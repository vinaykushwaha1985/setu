package com.gov.nha.bis.server.rest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FaceIdDetectService {
	
	
	
private static final Logger logger = LogManager.getLogger(OtpKycService.class);
	
	public String getFaceId(String faceId,String url) {
		
		String response="";
		
		response=getData(getRequestJson(faceId), url);
		
		return response;
	}
	
	
	public static String getRequestJson(String faceId){

		JSONObject data= new JSONObject();
		data.put("faceid", faceId);
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getData(String requestJson,String url){
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
