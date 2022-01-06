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
public class BeneficiaryAadhaarFetchSchemeDataService {
private static final Logger logger = LogManager.getLogger(BeneficiaryAadhaarFetchSchemeDataService.class);
@Autowired
ApplicationConstantConfig applicationConstantConfig;
	public String getAadhaarFetch(String aadhaar) {
		
		String response="";
		
		
		
		
		response=getAadhaarTokenFetch(getRequestJson(aadhaar), 
				applicationConstantConfig.BEN_AADHAAR_SCHEME_SEARCH_URL);
		
		return response;
	}
	public static String getRequestJson(String aadhaar){

		JSONObject data= new JSONObject();
		
		
		data.put("aadhaar", aadhaar);		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getAadhaarTokenFetch(String requestJson,String url){
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
