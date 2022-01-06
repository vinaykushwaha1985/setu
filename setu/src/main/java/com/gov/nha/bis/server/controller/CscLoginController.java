package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.HashMap;
import java.util.Locale;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.dto.CheckMobileNumberDto;
import com.gov.nha.bis.server.dto.UserProfileDto;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.SessionLoginMap;
import com.gov.nha.bis.server.model.UserForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.AppHistoryService;
import com.gov.nha.bis.server.rest.service.MobileNumberCheckService;
import com.gov.nha.bis.server.rest.service.OtpUidaiGenerationService;
import com.gov.nha.bis.server.rest.service.SmsOtpService;
import com.gov.nha.bis.server.rest.service.UserProfileDetailService;
import com.gov.nha.bis.server.util.CopyUtility;

@CrossOrigin
@Controller
public class CscLoginController {

	private static final Logger logger = LogManager.getLogger(CscLoginController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public UserProfileDetailService userProfileDetailService;

	@Autowired
	public MobileNumberCheckService mobileNumberCheckService;

	@Autowired
	public SmsOtpService smsOtpService;

	@Autowired
	public AppHistoryService appHistoryService;

	@RequestMapping(value = "/bis", method = RequestMethod.GET)
	public ModelAndView bisPage(Model model) {
		return new ModelAndView("bis", "command", new UserForm());
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView firstPage(Model model) {
		return new ModelAndView("home", "command", new UserProfileDto());
	}

	@RequestMapping(value = "/cscHome", method = RequestMethod.POST)
	public String homePage(HttpServletResponse response, HttpServletRequest request, UserForm user) {

		System.out.println(user.getUserName());

		String state = "" + (int) (Math.random() * 1000000);
		request.getSession(false).setAttribute("state", state);

		String connect_url = applicationConstantConfig.CSC_CONNECT_SERVER_URI + "authorize" + "?state=" + state
				+ "&response_type=code&client_id=" + applicationConstantConfig.CSC_CLIENT_ID + "&redirect_uri="
				+ applicationConstantConfig.CSC_CLIENT_CALLBACK_URI;

		logger.info("connect_url==" + connect_url);

		return "redirect:" + connect_url;

	}

	@RequestMapping(value = "/usrHome", method = RequestMethod.POST)
	public ModelAndView userLogin(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("userForm") UserForm userForm, BindingResult result) {
		SessionLoginMap sessionUser = new SessionLoginMap();
		String verifyUserId ="";
		HttpSession session=null;
		String roleId="";
		String appType="BIS2.0";
		String name="";
		try {
			session=request.getSession(true);

			if(request.getAttribute("verifyUserId")!=null) {
				verifyUserId =(String)request.getAttribute("verifyUserId");
				userForm.setUserName(verifyUserId);
			}

			if (applicationConstantConfig.OTP_ENBLE_FLAG.equalsIgnoreCase("Y")) {

				String profileRes = userProfileDetailService.authUserExist(userForm.getUserName(), "MOBILE");
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


						sessionUser.setMobile(userForm.getUserName());

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

						session.setAttribute("Bis_Login", "Bis_Login");
						session.setAttribute("sessionUser", sessionUser);
						session.setAttribute("USERID", userForm.getUserName());
						session.setAttribute("userType", "BIS2.0");
						session.setAttribute("roleId", roleId);
						session.setAttribute("IP", userForm.getIp());
						session.setAttribute("USERNAME", sessionUser.getName());

						appHistoryService.saveLoginDetails(userForm.getUserName(), userForm.getUserName(),
								roleId, appType, userForm.getIp(),sessionUser.getName());
						
						return new ModelAndView("beneficiarySearch", "command", new ApplicationForm());

					} else {
						request.setAttribute("error", "User is not registered. !!");
					}
				} else {
					request.setAttribute("error", "UMP Server not Respond !!");
				}
			} else {
				if (userForm.getPassword().equalsIgnoreCase("666666")) {


					String profileRes=userProfileDetailService.authUserExist(userForm.getUserName(), "MOBILE");

					if(!ObjectUtils.isEmpty(profileRes)) { JSONObject profileJsonRes= new
							JSONObject(profileRes);

					if(profileJsonRes.getString("statusCode").equalsIgnoreCase("1")) {

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


						sessionUser.setMobile(userForm.getUserName());

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

						session.setAttribute("Bis_Login", "Bis_Login");
						session.setAttribute("sessionUser", sessionUser);
						session.setAttribute("USERID", userForm.getUserName());
						session.setAttribute("userType", "BIS2.0");
						session.setAttribute("roleId", roleId);
						session.setAttribute("IP", userForm.getIp());
						session.setAttribute("USERNAME", sessionUser.getName());

						appHistoryService.saveLoginDetails(userForm.getUserName(), userForm.getUserName(),
								roleId, appType, userForm.getIp(),sessionUser.getName());
						
						return new ModelAndView("beneficiarySearch", "command", new ApplicationForm());

					}else { request.setAttribute("error", "User is not registered. !!"); } }else
					{ request.setAttribute("error", "User is not registered. !!"); }


				} else {
					request.setAttribute("error", "Invalid Credential !!");
				}
			}
			request.setAttribute("eMobile", userForm.getUserName());

		} catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		return new ModelAndView("home", "command", new UserForm());
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView userHome(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("userForm") UserForm userForm, BindingResult result) {

		return new ModelAndView("beneficiarySearch", "command", new ApplicationForm());

	}

	public static String getPenRqJosRequest(String userId) {

		JSONObject data = new JSONObject();
		data.put("userId", userId);

		return data.toString();
	}

	@RequestMapping(value = "/genMobileOtp", method = RequestMethod.POST)
	public @ResponseBody String genMobileOtp(HttpServletRequest request,
			@ModelAttribute("userProfileDto") UserProfileDto userProfileDto, Locale locale, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		String res = null;
		HashMap<String, String> otpMap = new HashMap<String, String>();

		OtpUidaiGenerationService otpAuthService = new OtpUidaiGenerationService();

		UserForm userForm = new UserForm();
		CopyUtility.copyProperties(userForm, userProfileDto, false);

		String response = otpAuthService.genMobileOtp(userProfileDto.getMobNumber().replaceAll(" ", ""),
				userProfileDto.getLoginId().replaceAll(" ", ""), userProfileDto.getStateCode().replaceAll(" ", ""),
				userProfileDto.getApplicationType().replaceAll(" ", ""),
				userProfileDto.getLoginMode().replaceAll(" ", ""), applicationConstantConfig.OTP_GEN_MOB_URL);
		logger.info("response====" + response);

		String userprofile = "";// userProfileDetailService.getUserProfileTwo(userProfileDto);
		logger.info("userprofile====" + userprofile);

		CheckMobileNumberDto mobilenumberDto = new CheckMobileNumberDto();
		CopyUtility.copyProperties(userForm, mobilenumberDto, false);

		String mobileNumberCheck = mobileNumberCheckService.checkMobileNumber(mobilenumberDto);
		logger.info("mobileNumberCheck====" + mobileNumberCheck);

		JSONObject demoRes = new JSONObject(response);
		if (Boolean.valueOf(demoRes.getString("status"))) {
			otpMap.put("status", "Y");
			otpMap.put("user_status", demoRes.getString("user_status"));
			otpMap.put("response_code", demoRes.getString("response_code"));
			otpMap.put("message", demoRes.getString("message"));
			otpMap.put("status", demoRes.getString("status"));
			otpMap.put("user_exists", demoRes.getString("user_exists"));
		} else {
			otpMap.put("status", "N");
			otpMap.put("user_status", demoRes.getString("user_status"));
			otpMap.put("response_code", demoRes.getString("response_code"));
			otpMap.put("message", demoRes.getString("message"));
			otpMap.put("status", demoRes.getString("status"));
			otpMap.put("user_exists", demoRes.getString("user_exists"));
		}

		try {
			res = mapper.writeValueAsString(otpMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return res;
	}

}
