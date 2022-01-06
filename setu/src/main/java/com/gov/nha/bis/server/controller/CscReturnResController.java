package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.BeneficiaryForm;
import com.gov.nha.bis.server.model.KycForm;
import com.gov.nha.bis.server.model.SeccForm;
import com.gov.nha.bis.server.model.SessionLoginMap;
import com.gov.nha.bis.server.model.UserForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.UmpDecryptJwtTokenService;
import com.gov.nha.bis.server.rest.service.UserProfileDetailService;

import bridgeutil.BridgePgUtil;

@CrossOrigin
@Controller
public class CscReturnResController {

	private static final Logger logger = LogManager.getLogger(CscReturnResController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public UmpDecryptJwtTokenService umpDecryptJwtTokenService;
	
	@Autowired
	public UserProfileDetailService userProfileDetailService;
	
	@Autowired
	public AppHistoryService appHistoryService;

	@RequestMapping(value="/usrAuthResponse", method = RequestMethod.POST)
	public ModelAndView returnCscResponse(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("userForm") UserForm userForm,Model model) {
		try {



			logger.info("userForm.getUmSecretKey()===="+userForm.getUmSecretKey());
			if(!ObjectUtils.isEmpty(userForm.getUmSecretKey())) {
				String resDecrypt=umpDecryptJwtTokenService.decryptJwtToken(userForm.getUmSecretKey());

				if(!ObjectUtils.isEmpty(resDecrypt)) {
					JSONObject resDecryptJsonRes = new JSONObject(resDecrypt);

					if (resDecryptJsonRes.getString("statusCode").equalsIgnoreCase("1")
							&& resDecryptJsonRes.getString("response").equalsIgnoreCase("Success")) {
						userForm.setUserName(resDecryptJsonRes.getString("loginId"));
						
						logger.info("resDecryptJsonRes.getString(\"loginId\")"+resDecryptJsonRes.getString("loginId"));
						request.setAttribute("verifyUserId", resDecryptJsonRes.getString("loginId"));
						return new ModelAndView("forward:/usrHome", "command",userForm);
						
					}else {
						request.setAttribute("error", "User Authentication Failed !!");
						return new ModelAndView("home", "command", new UserForm());
					}
					
					

				}else {
					request.setAttribute("error", "User Authentication Failed !!");
					return new ModelAndView("home", "command", new UserForm());
				}

			}else {
				request.setAttribute("error", "User Authentication Failed !!");
				return new ModelAndView("home", "command", new UserForm());
			}

		}catch (Exception e) {
			logger.info(e.getMessage());
			request.setAttribute("error", "UMP Server not respond !!");
			return new ModelAndView("home", "command", new UserForm());
			// TODO: handle exception
		}

		
	}

	public String sendPost(String url, String urlParameters) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}

	public static String getPenRqJosRequest(String userId){

		JSONObject data= new JSONObject();
		data.put("userId", userId);

		return data.toString();
	}

//Add csc login return url
	@RequestMapping(value="/cscLoginAuthResponse", method = RequestMethod.GET)
	public ModelAndView returnCscResponse(HttpServletResponse response, HttpServletRequest request,Model model) {
		try {

			//String profileRes1 = userProfileDetailService.authUserExist("577298060018", "MOBILE");
			SeccForm sccForm=new SeccForm();

			SessionLoginMap sessionUser = new SessionLoginMap();
			KycForm kycForm = new KycForm();

			String resp = request.getQueryString();

			String state_saved = (String)request.getSession(false).getAttribute("state");

			String verifyUserId ="";
			HttpSession session=null;
			String roleId="";
			String appType="BIS2.0";
			String name="";
			String state_req = request.getParameter("state");
			//System.out.println("resp==="+resp);
			logger.info("resp==="+resp);
			//System.out.println("state_saved==="+state_saved);
			logger.info("state_saved==="+state_saved);
			BridgePgUtil bc = new BridgePgUtil();
			String formattedClientSecret = bc.formClientSecret(applicationConstantConfig.CSC_CLIENT_SECRET, applicationConstantConfig.CSC_CLIENT_TOKEN);
			//System.out.println("formattedClientSecret==="+formattedClientSecret);
			logger.info(formattedClientSecret);

			if(state_saved == null || state_req == null ||	!state_saved.equals(state_req) ){
				resp = "State mismatched! Please try to login again.";
				//System.out.println("state_saved=== iff");
				request.setAttribute("error", "Invalid Credential !!");
				return new ModelAndView("home", "command", new UserForm());
			} else {
				String code = request.getParameter("code");

				if(code == null || code.length() <= 0){
					resp = "Error!! Code not received from server!";
					request.setAttribute("error", resp);
					return new ModelAndView("home", "command", new UserForm());
				} 
				else {
					//jsp post a request ..
					String url = applicationConstantConfig.CSC_CONNECT_SERVER_URI+"token";

					logger.info("url token access===="+url);
					//System.out.println("url token access===="+url);
					String parameters =
							"code=" + code + "&redirect_uri=" + applicationConstantConfig.CSC_CLIENT_CALLBACK_URI + 
							"&grant_type=authorization_code"+ 
							"&client_id=" + applicationConstantConfig.CSC_CLIENT_ID +
							"&client_secret="+formattedClientSecret;
					
					logger.info("parameters token acess"+parameters);
					//System.out.println("parameters token acess"+parameters);
					try{
						String ret = sendPost(url, parameters);

						logger.info("ret===="+ret);
						//System.out.println("ret===="+ret);
						JSONObject tResp = new JSONObject(ret);
						String token = tResp.getString("access_token");
						parameters = "access_token=" + token;
						//System.out.println("resource url==="+applicationConstantConfig.CSC_CONNECT_SERVER_URI+"resource");
						logger.info("resource url==="+applicationConstantConfig.CSC_CONNECT_SERVER_URI+"resource");
						resp = sendPost(applicationConstantConfig.CSC_CONNECT_SERVER_URI+"resource", parameters);




						logger.info("final resp==="+resp);
						JSONObject obj = new JSONObject(resp);
						JSONObject user = obj.getJSONObject("User");

						Map<String, Object> mVals = user.toMap();

						resp = "\n<br>----User Details ----";

						for (Map.Entry<String, Object> entry :
							mVals.entrySet()) {
							logger.info("Key = " + entry.getKey() +
									", Value = " + entry.getValue());
							resp += "\n" + entry.getKey() + " : " +
									entry.getValue();
						}
						request.getSession().setAttribute("USERID",mVals.get("username"));
						request.getSession(true).setAttribute("userType","");
						if(ObjectUtils.isEmpty(mVals.get("username"))) {	
							request.setAttribute("error", "Invalid Credential !!");

							return new ModelAndView("home", "command", new UserForm());
						}else {
							
							//
							
							
							//
							
							
//							CscUserForm cscForm= new CscUserForm();
//							
//							cscForm.setFullname(mVals.get("fullname").toString());
//							cscForm.setEmail(mVals.get("email").toString());
//							cscForm.setOwner(mVals.get("owner").toString());
//							cscForm.setLg_state_code(mVals.get("lg_state_code").toString());
//							cscForm.setLg_district_code(mVals.get("lg_district_code").toString());
//							cscForm.setCsc_id(mVals.get("csc_id").toString());
//							cscForm.setState_code(mVals.get("state_code").toString());
//							cscForm.setUser_type(mVals.get("user_type").toString());
//							cscForm.setActive_status(mVals.get("active_status").toString());
//							cscForm.setLast_active(mVals.get("last_active").toString());
//							cscForm.setPOS(mVals.get("RAP").toString());
//							cscForm.setRAP(mVals.get("POS").toString());
//							cscForm.setVle_check(mVals.get("vle_check").toString());
//							session.invalidate();
//							session=request.getSession(true);
//							sessionUser.setAdr("");
//							sessionUser.setDistrict(mVals.get("lg_district_code").toString());
//							session.setAttribute("Bis_Login", "Bis_Login");
//							session.setAttribute("sessionUser", sessionUser);
//							session.setAttribute("USERID", mVals.get("csc_id").toString());
//							session.setAttribute("userType", "BIS2.0");
//							//session.setAttribute("roleId", roleId);
//							//session.setAttribute("IP", userForm.getIp());
//							session.setAttribute("USERNAME", mVals.get("fullname").toString());
							
//							request.getSession(true).setAttribute("cscForm",  cscForm);
//							request.getSession(true).setAttribute("CSC_USER",  "Y");
//							request.getSession(true).setAttribute("Bis_Login",  "Bis_Login");
							//

							String profileRes = userProfileDetailService.authUserExist(mVals.get("username").toString(), "MOBILE");
							logger.info("profileRes===" + profileRes);
							if (!ObjectUtils.isEmpty(profileRes)) {
								JSONObject profileJsonRes = new JSONObject(profileRes);

								if (profileJsonRes.getString("statusCode").equalsIgnoreCase("1")) {

									session.invalidate();
									session=request.getSession(true);

									if(profileJsonRes.has("roleAppDtls")) {
										JSONArray jsonArr=profileJsonRes.getJSONArray("roleAppDtls");	

										for (int i = 0; i < jsonArr.length(); i++) {

											JSONObject appArry = jsonArr.getJSONObject(i);
											if(appArry.getString("appType").equalsIgnoreCase(appType)) {
												roleId=appArry.getString("roleIdName");
											}
										}
									}
									if(profileJsonRes.has("ekycDistrict"))
										sessionUser.setDistrict(profileJsonRes.getString("ekycDistrict"));

									if(profileJsonRes.has("ekycState"))
										sessionUser.setState(profileJsonRes.getString("ekycState"));

									if(profileJsonRes.has("ekycName")) {
										sessionUser.setName(profileJsonRes.getString("ekycName"));
										name=profileJsonRes.getString("ekycName");
									}

									if(profileJsonRes.has("ekycGender"))
										sessionUser.setGender(profileJsonRes.getString("ekycGender"));


									sessionUser.setMobile(mVals.get("username").toString());

									if(profileJsonRes.has("ekycPin"))
										sessionUser.setPin(profileJsonRes.getString("ekycPin"));

									if(profileJsonRes.has("ekycdateOfBirth"))
										sessionUser.setDob(profileJsonRes.getString("ekycdateOfBirth"));

									if(profileJsonRes.has("ekycAddress"))
										sessionUser.setAdr(profileJsonRes.getString("ekycAddress"));

									if(profileJsonRes.has("ekycPin"))
										sessionUser.setPin(profileJsonRes.getString("ekycPin"));

									if(profileJsonRes.has("photo"))
										sessionUser.setPht(profileJsonRes.getString("photo"));
									InetAddress addr = InetAddress.getLocalHost();
									String ip = addr.getHostAddress();
									session.setAttribute("Bis_Login", "Bis_Login");
									session.setAttribute("sessionUser", sessionUser);
									session.setAttribute("USERID", mVals.get("username").toString());
									session.setAttribute("userType", "BIS2.0");
									session.setAttribute("roleId", roleId);
									session.setAttribute("IP", ip);
									session.setAttribute("USERNAME", sessionUser.getName());

									appHistoryService.saveLoginDetails(mVals.get("username").toString(), mVals.get("username").toString(),
											roleId, appType, ip,sessionUser.getName());
									return new ModelAndView("beneficiarySearch", "command", new ApplicationForm());

								} else {
									request.setAttribute("error", "User is not registered. Please <a href=\"https://users.nha.gov.in/UserManagement/verifyEkyc.htm\">click here.</a>");

									return new ModelAndView("home", "command", new UserForm());
//									return new ModelAndView(applicationConstantConfig.CSC_USER_REGISTRATION_URL, "command", new UserForm());
								}
							} else {
								request.setAttribute("error", "UMP Server not Respond !!");
								return new ModelAndView("home", "command", new UserForm());
							}
						
							//
							


						}
					}catch(Exception ec){
						ec.printStackTrace();
						logger.info("exception===" + ec.getMessage());
						request.setAttribute("error", "Invalid Credential !!");
						return new ModelAndView("home", "command", new UserForm());

					}
				}
			}
			
		}catch (Exception e) {
			logger.info("exception=11==" + e.getMessage());
		}

		return new ModelAndView("beneficiarySearch", "command", new ApplicationForm());
	}
}
