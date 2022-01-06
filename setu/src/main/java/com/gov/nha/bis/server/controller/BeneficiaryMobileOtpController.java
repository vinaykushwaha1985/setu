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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.nha.bis.server.model.ApplicationForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.CardDownloadService;
import com.gov.nha.bis.server.rest.service.PmjayIdService;
import com.gov.nha.bis.server.rest.service.SmsOtpService2;


@Controller
public class BeneficiaryMobileOtpController {



	@Autowired
	public SmsOtpService2 smsOtpService;

	@Autowired
	public PmjayIdService pmjayIdService;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public CardDownloadService cardDownloadService;



	private static final Logger logger = LogManager.getLogger(BeneficiaryMobileOtpController.class);


	@RequestMapping(value="/benMobileOtpSender",method = RequestMethod.POST)
	public @ResponseBody String benMobileOtpSender(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> otpMap = new  HashMap<String, String> ();

		String pmayIdRes=pmjayIdService.getPmjayId( applicationForm.getStateCode(),applicationForm.getGuid());
		logger.info("response===="+pmayIdRes);
		if(!ObjectUtils.isEmpty(pmayIdRes)) {
			JSONObject jsonRes= new JSONObject(pmayIdRes);
			if(jsonRes.getString("status").equalsIgnoreCase("Y")) {	
				if(jsonRes.has("pmjay_id"))
					otpMap.put("pmjayId", jsonRes.getString("pmjay_id"));

				if(applicationForm.getOtpType().equalsIgnoreCase("M"))	{
					if(jsonRes.getString("mobileNo").equalsIgnoreCase(applicationForm.getAadhar())) {

						if(applicationConstantConfig.OTP_ENBLE_FLAG.equalsIgnoreCase("Y")) {	

							String	response=smsOtpService.sendSmsOtp(jsonRes.getString("mobileNo"),"1007163593280345232");


							logger.info("response===="+response);
							if(!ObjectUtils.isEmpty(response)) {
								JSONObject demoRes= new JSONObject(response);
								if(Boolean.valueOf(demoRes.getString("status"))) {
									otpMap.put("status", "Y");
									otpMap.put("txn", demoRes.getString("txn"));
								}else {
									otpMap.put("status", "N");
								}
							}else {
								otpMap.put("status", "N");
							}
						}else {
							otpMap.put("status", "Y");

						}

					}else { otpMap.put("status", "MN"); }

				}else {
					if(applicationConstantConfig.OTP_ENBLE_FLAG.equalsIgnoreCase("Y")) {	
						String	response=smsOtpService.sendSmsOtp(jsonRes.getString("mobileNo"),"1007163593280345232");

						logger.info("response===="+response);
						if(!ObjectUtils.isEmpty(response)) {
							JSONObject demoRes= new JSONObject(response);
							if(Boolean.valueOf(demoRes.getString("status"))) {
								otpMap.put("status", "Y");
								otpMap.put("txn", demoRes.getString("txn"));
							}else {
								otpMap.put("status", "N");
							}
						}else {
							otpMap.put("status", "N");
						}
					}else {
						otpMap.put("status", "Y");
					}
				}

			}else {
				otpMap.put("status", "NN");
			}
		}
		try {
			res = mapper.writeValueAsString(otpMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return res;
	}


	@RequestMapping(value="/verifySmsMobileOtp",method = RequestMethod.POST)
	public ModelAndView otpUidaiAuth(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		try {
			if(applicationConstantConfig.OTP_ENBLE_FLAG.equalsIgnoreCase("Y")) {

				String	response=smsOtpService.verifySmsOtp(applicationForm.getAadhar().replaceAll(" ", ""),
						applicationForm.getOtp(),"1007163593280345232");

				logger.info("response===="+response);
				JSONObject demoRes= new JSONObject(response);
				if(Boolean.valueOf(demoRes.getString("status"))) {

					logger.info(applicationForm.getStateCode().split(",")[0]+"    "+applicationForm.getRefernceid().split(",")[0]);


					String cardResponse =cardDownloadService.cardDownload(applicationForm.getStateCode().split(",")[0], applicationForm.getPmjayId().split(",")[0],null, "BIS2.0",applicationConstantConfig.NHA_BIS_CARD_DOWNLOAD_URL);
					logger.info("card response "+cardResponse);
					JSONObject jRes= new JSONObject(cardResponse);
					if(jRes.getBoolean("status")) {

						applicationForm.setCardPht(jRes.getString("card"));

						request.setAttribute("cardPht", jRes.getString("card"));
						return new ModelAndView("forward:/benCardDonwload", "command",applicationForm);
					}else {
						request.setAttribute("status", "F");
					}

				}else {
					request.setAttribute("status", "N");
				}

			}else {


				if(applicationForm.getOtp().equalsIgnoreCase("666666")) {

					logger.info(applicationForm.getStateCode()+"    "+applicationForm.getRefernceid()+"   "+applicationForm.getGuid());


					String cardResponse =cardDownloadService.cardDownload(applicationForm.getStateCode(), applicationForm.getRefernceid(),null, "BIS2.0",applicationConstantConfig.NHA_BIS_CARD_DOWNLOAD_URL);
					logger.info("card response "+cardResponse);
					JSONObject jRes= new JSONObject(cardResponse);
					if(jRes.getBoolean("status")) {

						applicationForm.setCardPht(jRes.getString("card"));

						request.setAttribute("cardPht", jRes.getString("card"));
						return new ModelAndView("forward:/benCardDonwload", "command",applicationForm);
					}else {
						request.setAttribute("status", "F");
					}

				}else {
					request.setAttribute("status", "N");
				}

			}
		}catch (Exception e) {
			request.setAttribute("status", "N");
			logger.info(e.getMessage());// TODO: handle exception
		}
		return new ModelAndView("beneficiaryCardDonwload", "command",applicationForm);
	}



	@RequestMapping(value="/benAddMobileOtpSender",method = RequestMethod.POST)
	public @ResponseBody String benAddMobileOtpSender(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{
		ObjectMapper mapper = new ObjectMapper();
		String res=null;
		HashMap<String, String> otpMap = new  HashMap<String, String> ();
		try {
			String pmayIdRes=pmjayIdService.getPmjayId( applicationForm.getStateCode(),applicationForm.getGuid());
			logger.info("response===="+pmayIdRes);
			if(!ObjectUtils.isEmpty(pmayIdRes)) {
				JSONObject jsonRes= new JSONObject(pmayIdRes);
				if(jsonRes.getString("status").equalsIgnoreCase("Y")) {	
					if(jsonRes.has("pmjay_id"))
						otpMap.put("pmjayId", jsonRes.getString("pmjay_id"));

					if(applicationForm.getOtpType().equalsIgnoreCase("M"))	{
						if(jsonRes.getString("mobileNo").equalsIgnoreCase(applicationForm.getAadharMobile())) {

							if(applicationConstantConfig.OTP_ENBLE_FLAG.equalsIgnoreCase("Y")) {	

								String	response=smsOtpService.sendSmsOtp(jsonRes.getString("mobileNo"),"1007163593301170578");

								logger.info("response===="+response);
								if(!ObjectUtils.isEmpty(response)) {
									JSONObject demoRes= new JSONObject(response);
									if(Boolean.valueOf(demoRes.getString("status"))) {
										otpMap.put("status", "Y");
										otpMap.put("txn", demoRes.getString("txn"));
									}else {
										otpMap.put("status", "N");
									}
								}else {
									otpMap.put("status", "N");
								}
							}else {
								otpMap.put("status", "Y");

							}

							 }else { otpMap.put("status", "MN"); } 

					}else {
						if(applicationConstantConfig.OTP_ENBLE_FLAG.equalsIgnoreCase("Y")) {	
							String	response=smsOtpService.sendSmsOtp(jsonRes.getString("mobileNo"),"1007163593301170578");

							logger.info("response===="+response);
							if(!ObjectUtils.isEmpty(response)) {
								JSONObject demoRes= new JSONObject(response);
								if(Boolean.valueOf(demoRes.getString("status"))) {
									otpMap.put("status", "Y");
									otpMap.put("txn", demoRes.getString("txn"));
								}else {
									otpMap.put("status", "N");
								}
							}else {
								otpMap.put("status", "N");
							}
						}else {
							otpMap.put("status", "Y");
						}
					}

				}else {
					otpMap.put("status", "NN");
				}
			}
			try {
				res = mapper.writeValueAsString(otpMap);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
			}
		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
		}
		return res;
	}


	@RequestMapping(value="/addFamilyMemeber",method = RequestMethod.POST)
	public ModelAndView verifyAddMemMobileOtp(HttpServletRequest request,
			@ModelAttribute("applicationForm") ApplicationForm applicationForm,
			Locale locale,Model model)
	{

		try {

			String	response=smsOtpService.verifySmsOtp(applicationForm.getAadharMobile().replaceAll(" ", ""),
					applicationForm.getOtpAddMem(),"1007163593301170578");

			logger.info("response===="+response);

			JSONObject demoRes= new JSONObject(response);
			if(Boolean.valueOf(demoRes.getString("status"))) {

				return new ModelAndView("forward:/benFemDetails", "command",applicationForm);
			}else {
				request.setAttribute("status", "AN");
			}
		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
			request.setAttribute("status", "AN");
		}


		return new ModelAndView("beneficiaryCardDonwload", "command",applicationForm);
	}





}
