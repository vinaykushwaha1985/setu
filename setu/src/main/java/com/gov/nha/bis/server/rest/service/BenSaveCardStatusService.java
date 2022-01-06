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
public class BenSaveCardStatusService {



	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(BenSaveCardStatusService.class);
	
	public String benSaveCardStatus(String refernceid,String stateCode,String status,String urFlag,String usrId) {
		
		String response="";
		
		response=getSaveDemo(getbeneficiaryUpdateStatusRequestJson(refernceid, stateCode,
				status,urFlag,usrId), applicationConstantConfig.UPDATE_CARD_STATUS_URL);
		
		return response;
	}
	
	
	public  String getbeneficiaryUpdateStatusRequestJson(String refernceid,String stateCode,String status,String ruFalg, String userid){

		JSONObject data= new JSONObject();
		
		data.put("guid", refernceid);
		data.put("statecode", stateCode);
		data.put("card_status", status);
		data.put("rural_urban", ruFalg);
		data.put("userid", userid);
		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
		
	public  String getSaveDemo(String requestJson,String url){
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
