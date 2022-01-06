/**
 * 
 */
package com.gov.nha.bis.server.rest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Service
public class SmsOtpService2 {

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(SmsOtpService2.class);

	public  String sendSmsOtp(String mobile,String templateId) {

		String response="";

		response=sendSms(sendSmsOtpRequest(mobile,templateId),applicationConstantConfig.BIS_OTP_SEND_URL);

		return response;
	}

	public  String verifySmsOtp(String mobile,String otp,String templateId) {

		String response="";

		response=sendSms(verifySmsOtpRequest(mobile,otp,templateId), applicationConstantConfig.BIS_OTP_VERIFY_URL);

		return response;
	}


	public  String sendSmsOtpRequest(String mobile,String templateId){

		JSONObject data= new JSONObject();
		data.put("mobile", mobile);
		data.put("templateid", templateId);

		logger.info(data.toString());
		return data.toString();
	}

	public  String verifySmsOtpRequest(String mobile,String otp,String templateId){

		JSONObject data= new JSONObject();
		data.put("mobile", mobile);
		data.put("otp", otp);
		data.put("templateid", templateId);
		logger.info(data.toString());
		return data.toString();
	}


	public   String sendSms(String requestJson,String url){
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





}
