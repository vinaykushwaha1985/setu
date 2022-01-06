package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.config.token.NhaBisCardAccessTokenService;
import com.gov.nha.bis.server.config.token.NhaBisGetProfileService;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class OtpUidaiGenerationService {
	
	private static final Logger logger = LogManager.getLogger(OtpUidaiGenerationService.class);
	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	@Autowired
	public NhaBisGetProfileService nhaBisGetProfileService;
	
	public String genOtp(String aadhaar,String url) {
		
		String response="";
		OtpUidaiGenerationService otpAuthService= new OtpUidaiGenerationService();
		
		response=otpAuthService.getOtp(getOtpRequestJson(aadhaar), url);
		
		return response;
	}
	
	
	public static String getOtpRequestJson(String aadhaar){

		JSONObject data= new JSONObject();
		data.put("aadhaar", aadhaar);
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getOtp(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(url, entity, String.class);
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return returnStr;
	}

	public  String getMobOtp(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(url, entity, String.class);
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return returnStr;
	}
	public String genMobileOtp(String mobNumber, String loginId, String stateCode, String applicationType,
			String loginMode, String url) {
		String response="";
		OtpUidaiGenerationService otpAuthService= new OtpUidaiGenerationService();
		
		response=otpAuthService.getMobOtp(getMobileOtpRequestJsonTest(mobNumber,loginId,stateCode,applicationType,loginMode), url);
		
		return response;
	}
	
	public static String getMobileOtpRequestJsonTest(String mobNumber,String loginId,String stateCode,String applicationType,String loginMode){

		JSONObject data= new JSONObject();
		data.put("mobNumber", mobNumber);
		data.put("loginId", loginId);
		data.put("stateCode", stateCode);
		data.put("applicationType", applicationType);
		data.put("loginMode", loginMode);
		logger.info(data.toString());
		return data.toString();
	}
	
}



