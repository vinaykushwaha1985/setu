package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.model.UserForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.SmsOtpService;
import com.gov.nha.bis.server.rest.service.UmpJwtTokenService;
import com.gov.nha.bis.server.rest.service.UserAuthenticationService;
import com.gov.nha.bis.server.rest.service.UserProfileDetailService;

@CrossOrigin
@Controller
public class MobileSmsSendOtpController {

	@Autowired
	public SmsOtpService smsOtpService;

	@Autowired
	public UserProfileDetailService userProfileDetailService;

	@Autowired
	public UserAuthenticationService  userAuthenticationService;

	@Autowired
	public  UmpJwtTokenService  umpJwtTokenService;


	private static final Logger logger = LogManager.getLogger(MobileSmsSendOtpController.class);

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;



	@RequestMapping(value="/smsOtpSender",method = RequestMethod.POST)
	public @ResponseBody String genOtp(HttpServletRequest request,
			@ModelAttribute("userForm") UserForm userForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> otpMap = new  HashMap<String, String> ();

		String userRes=userAuthenticationService.authUserExist(userForm.getUserName(),"MOBILE");
		
		logger.info("userRes==="+userRes);

		if(!ObjectUtils.isEmpty(userRes)) {	

			JSONObject userJson= new JSONObject(userRes);
			if(Boolean.valueOf(userJson.getString("status"))) {

				if(userJson.getString("user_exists").equalsIgnoreCase("Y")) {
					if(userJson.getString("user_status").equalsIgnoreCase("ACTIVE")) {


						String jwtRes=	umpJwtTokenService.jwtToken(userForm.getUserName(),"Mobile OTP");
						logger.info("jwtRes===="+jwtRes);
						if(!ObjectUtils.isEmpty(jwtRes)) {
							JSONObject jwtJosn= new JSONObject(jwtRes);
							if(jwtJosn.getString("statusCode").equalsIgnoreCase("1")) {

								otpMap.put("umpUrl", applicationConstantConfig.UMP_USER_AUTHENTICATION_URL);
								otpMap.put("jwtToken" , jwtJosn.getString("jwtToken"));
								otpMap.put("status", "Y");
							}
						}else {
							otpMap.put("status", "R"); 
						}

					}else {
						otpMap.put("status", "DE");
					}

				}else if(userJson.getString("user_exists").equalsIgnoreCase("N")) { 

					String jwtRes=	umpJwtTokenService.jwtToken(userForm.getUserName(),"MOBILE_OTP");
					logger.info("jwtRes===="+jwtRes);
					if(!ObjectUtils.isEmpty(jwtRes)) {
						JSONObject jwtJosn= new JSONObject(jwtRes);
						if(jwtJosn.getString("statusCode").equalsIgnoreCase("1")) {

							otpMap.put("umpUrl", applicationConstantConfig.UMP_USER_SELF_REGISTRATION_URL);
							otpMap.put("jwtToken" , jwtJosn.getString("jwtToken"));
							otpMap.put("status", "UN");
						}
					}else {
						otpMap.put("status", "R"); 
					}
				}
			} else { 

				String jwtRes=	umpJwtTokenService.jwtToken(userForm.getUserName(),"MOBILE_OTP");
				logger.info("jwtRes===="+jwtRes);
				if(!ObjectUtils.isEmpty(jwtRes)) {
					JSONObject jwtJosn= new JSONObject(jwtRes);
					if(jwtJosn.getString("statusCode").equalsIgnoreCase("1")) {

						otpMap.put("umpUrl", applicationConstantConfig.UMP_USER_SELF_REGISTRATION_URL);
						otpMap.put("jwtToken" , jwtJosn.getString("jwtToken"));
						otpMap.put("status", "UN");
					}
				}else {
					otpMap.put("status", "R"); 
				}
			}
		}else { 
			otpMap.put("status", "R"); 
		}

		try {
			res = mapper.writeValueAsString(otpMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value="/verifySmsOtp",method = RequestMethod.POST)
	public ModelAndView otpUidaiAuth(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		String	response=smsOtpService.verifySmsOtp(applicationForm.getAadhar().replaceAll(" ", ""),
				applicationForm.getOtp());

		logger.info("response===="+response);
		JSONObject demoRes= new JSONObject(response);
		if(Boolean.valueOf(demoRes.getString("status"))) {

			logger.info(applicationForm.getStateCode()+"    "+applicationForm.getRefernceid()+"   "+applicationForm.getGuid());


			return new ModelAndView("forward:/benCardDonwload", "command",applicationForm);


		}else {
			request.setAttribute("status", "N");
		}
		return new ModelAndView("beneficiaryCardDonwload", "command",applicationForm);
	}



}
