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

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class FuzzyMatcherService {

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(FuzzyMatcherService.class);
	
	public String getFuzzyMatcherScore(String nameBy,String genderBy,String fatherBy,String nameWith,String genderWith,String fatherWith) {
		
		String response="";
		
		response=getScore(getRequestJson( nameBy, genderBy, fatherBy, nameWith, genderWith, fatherWith), 
				applicationConstantConfig.NHA_BIS_FUZZY_SERVICE_URL);
		
		return response;
	}
	
	
	public static String getRequestJson(String nameBy,String genderBy,String fatherBy,String nameWith,String genderWith,String fatherWith){

		JSONObject data= new JSONObject();
		
		JSONObject databy= new JSONObject();
		
		databy.put("name", nameBy);
		databy.put("gender", genderBy);
		databy.put("guardian", fatherBy);
		
		JSONObject datawith= new JSONObject();
		datawith.put("name", nameWith);
		datawith.put("gender", genderWith);
		datawith.put("guardian", fatherWith);
		
		data.put("matcherBy", databy);
		data.put("matcherWith", datawith);
		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getScore(String requestJson,String url){
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
