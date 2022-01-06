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
public class CardSchemeStatusService {
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(CardStatusService.class);
	
	public String getCardStatus(String state_code,String district_code,String schemeid,String beneficiaryid,String rural_urban) {

		String response="";

		response=getData(getRequestJson(state_code,district_code,schemeid,beneficiaryid,rural_urban), 
				applicationConstantConfig.BEN_SCHEME_CARD_FETCH_DATA_URL);

		return response;
	}




	private String getRequestJson(String state_code,String district_code,String schemeid,String beneficiaryid,String rural_urban) {
		JSONObject data= new JSONObject();

		data.put("state_code", state_code);
		data.put("district_code", district_code);
		data.put("schemeid", schemeid);
		data.put("beneficiaryid", beneficiaryid);		
		data.put("rural_urban", rural_urban);

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
