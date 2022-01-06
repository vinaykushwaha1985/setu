package com.gov.nha.bis.server.rest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Controller
public class BeneficiaryMmjaUpdateStatusService {

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(BeneficiaryMmjaUpdateStatusService.class);

	public String beneficiaryUpdateStatus(String refernceid,String stateCode,String status,String urFlag) {

		String response="";
				if(urFlag.equalsIgnoreCase("R"))
				response=getSaveDemo(getSaveDemoRequestJson(refernceid, stateCode,
						status,urFlag), applicationConstantConfig.MMJA_RURAL_BEN_SEARCH_URL);
			else if(urFlag.equalsIgnoreCase("U"))
				response=getSaveDemo(getSaveDemoRequestJson(refernceid, stateCode,
						status,urFlag), applicationConstantConfig.MMJA_URBAN_BEN_SEARCH_URL);
		return response;
	}


	public static String getSaveDemoRequestJson(String refernceid,String stateCode,String status,String urFlag){

		JSONObject data= new JSONObject();
		data.put("guid", refernceid);
		data.put("stateCode", stateCode);
		data.put("card_status", status);
		data.put("rural_urban", urFlag);

		logger.info(data.toString());
		return data.toString();
	}


	public static String getRuralRequestJson(String refernceid,String stateCode,String districtCode,
			String blockCode,String villageCode,String status,String urFlag,String userid){

		JSONObject data= new JSONObject();
		data.put("guid", refernceid);
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("blockCode", blockCode);
		data.put("villageCode", villageCode);
		data.put("card_status", status);
		data.put("urFlag", urFlag);
		data.put("userid", userid);


		logger.info(data.toString());
		return data.toString();
	}


	public static String getUrbanRequestJson(String refernceid,String stateCode,String districtCode,
			String townCode,String wardCode,String status,String urFlag,String userid){

		JSONObject data= new JSONObject();
		data.put("guid", refernceid);
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("townCode", townCode);
		data.put("wardCode", wardCode);
		data.put("card_status", status);
		data.put("urFlag", urFlag);
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
