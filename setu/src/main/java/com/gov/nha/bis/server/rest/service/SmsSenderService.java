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

@Service
public class SmsSenderService {


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(SmsSenderService.class);

	public  String smsSender(String mobile,String templateId,String name,String refId) {

		String response="";

		response=sendSms(sendSmsOtpRequest(mobile,templateId, name, refId),applicationConstantConfig.NHA_SMS_SEND_URL);

		return response;
	}

	
	public  String sendSmsOtpRequest(String mobile,String templateId,String name,String refId){

		JSONObject data= new JSONObject();
		data.put("mobile", mobile);
		data.put("templateid", templateId);
		data.put("name", name);
		data.put("refId", refId);
		
		
		

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
