package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class PendingReqIdService {
	
	private static final Logger logger = LogManager.getLogger(PendingReqIdService.class);
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	
	public  String getPendingReqIdData(String requestJson,String reqUrl){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(reqUrl, entity, String.class);
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return returnStr;
	}
	
	
	public  String getSeccEkycRefData(String requestJson,String reqUrl){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(reqUrl, entity, String.class);
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return returnStr;
	}
	
	
	
	public  String getUpdateBenCheckStatusRefData(String requestJson,String reqUrl){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(reqUrl, entity, String.class);
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return returnStr;
	}
}
