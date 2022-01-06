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
public class BeneficiaryGuidService {
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(BeneficiaryGuidService.class);
	
	
	public String getRuralGuidData(String guid,String stateCode, String districtCode,String blockCode,
			String villageCode,String urFlag) {
		return getGuidData(getRuralGuidRequestJson(guid, stateCode, districtCode, blockCode, villageCode, urFlag),applicationConstantConfig.GUID_DATA_FETCH_URL);
		
	}
	
	public String getUrbanGuidData(String guid,String stateCode, String districtCode,String townCode,
			String wardCode ,String urFlag) {
		return getGuidData(getUrbanGuidRequestJson(guid, stateCode, districtCode, townCode, wardCode , urFlag),applicationConstantConfig.GUID_DATA_FETCH_URL);
	}
	
	
	
	public  String getGuidData(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{
			
			logger.info("Beneficiary Search request===::"+requestJson);
			logger.info("Search URL===::"+url);
			
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


	public static String getRuralGuidRequestJson(String guid,String stateCode, String districtCode,String blockCode,
			String villageCode,String urFlag){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("guid", guid);
		data.put("districtCode", districtCode);
		data.put("blockCode", blockCode);
		data.put("villageCode", villageCode);
		data.put("urFlag", urFlag);

		return data.toString();
	}

	public static String getUrbanGuidRequestJson(String guid,String stateCode, String districtCode,String townCode,
			String wardCode ,String urFlag){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("guid", guid);
		data.put("districtCode", districtCode);
		data.put("wardCode", wardCode);
		data.put("townCode", townCode);
		data.put("urFlag", urFlag);

		return data.toString();
	}




}
