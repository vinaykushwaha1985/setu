package com.gov.nha.bis.server.config.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RationCardAccessTokenService {

	
	private static final Logger logger = LoggerFactory.getLogger(NhaBisCardAccessTokenService.class);
	
	
	
	public   String sendReq(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			System.out.println("requestJson"+requestJson);
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
