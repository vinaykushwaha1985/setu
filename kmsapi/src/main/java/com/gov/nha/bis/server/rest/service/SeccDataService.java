package com.gov.nha.bis.server.rest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.FamilySeccParam;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class SeccDataService {

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(SeccDataService.class);
	
	public String saveSeccData(FamilySeccParam rPram) {
		
		String response="";
		Gson gson = new Gson();
		
		response=getSave(gson.toJson(rPram), applicationConstantConfig.DB_BEN_SECC_DATA_SAVE_URL);
		
		return response;
	}
	
		
	
	public  String getSave(String requestJson,String url){
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
