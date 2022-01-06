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
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;

@Service
public class SaveDemoDataService {

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(SaveKycDataService.class);

	public String saveDemoDeclareData(String refernceid,String name,String gender,
			String dob,String pncode,String guardian,String mobile,String aadhaar,String stateCode,String ruflag,
			String districtCode,String blockCode,String villageCode,String townCode,String wardCode,String ekyctype) {

		String response="";

		if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.SEARCH_SERVICE_REDIS_ENABLE_LIST.split(",")).contains(stateCode)) {
			response=getSaveDemo(getSaveDemoRequestJson(refernceid, name, gender,
					dob, pncode, guardian, mobile, aadhaar,stateCode,ruflag,
					districtCode, blockCode, villageCode, townCode, wardCode,ekyctype), applicationConstantConfig.REDIS_DEMO_DECLARE_DATA_SAVE_URL);

		}else {
			response=getSaveDemo(getSaveDemoRequestJson(refernceid, name, gender,
					dob, pncode, guardian, mobile, aadhaar,stateCode,ruflag,
					districtCode, blockCode, villageCode, townCode, wardCode,ekyctype), applicationConstantConfig.DEMO_DECLARE_DATA_SAVE_URL);

		}
		return response;
	}


	public String fetchDemoDeclareData(String refernceid,String stateCode,String ruflag,int status) {

		String response="";

		if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.SEARCH_SERVICE_REDIS_ENABLE_LIST.split(",")).contains(stateCode)) {
			response=getSaveDemo(fetchDemoRedisRequestJson(refernceid, stateCode, ruflag,
					status), applicationConstantConfig.REDIS_DEMO_DECLARE_FETCH_URL);
		}else {	
			response=getSaveDemo(fetchDemoRequestJson(refernceid, stateCode, ruflag,
					status), applicationConstantConfig.NAH_BIS_DEMO_AUTH_FETCH_URL);
		}
		return response;
	}


	private String fetchDemoRequestJson(String refernceid, String stateCode, String ruflag, int status) {
		// TODO Auto-generated method stub
		JSONObject data= new JSONObject();
		data.put("refernceid", refernceid);
		data.put("statecode", stateCode);
		data.put("ruflag", ruflag);
		data.put("status", status);


		logger.info(data.toString());
		return data.toString();
	}


	private String fetchDemoRedisRequestJson(String refernceid, String stateCode, String ruflag, int status) {
		// TODO Auto-generated method stub
		JSONObject data= new JSONObject();
		data.put("guid", refernceid);
		data.put("stateCode", stateCode);
		data.put("ruralUrban", ruflag);
		data.put("status", status);


		logger.info(data.toString());
		return data.toString();
	}

	public static String getSaveDemoRequestJson(String refernceid,String name,String gender,
			String dob,String pncode,String guardian,String mobile,String aadhaar,String stateCode,String ruflag,
			String districtCode,String blockCode,String villageCode,String townCode,String wardCode,String ekyctype){

		JSONObject data= new JSONObject();
		data.put("refernceid", refernceid);
		data.put("name", name);
		data.put("gender", gender);
		data.put("dob", dob);
		data.put("statecode", stateCode);
		data.put("pncode", pncode); 
		data.put("guardian", guardian);
		data.put("mobile", mobile);
		data.put("aadhaar", aadhaar);
		data.put("ruflag", ruflag);

		data.put("ekyctype", ekyctype);
		data.put("districtCode", districtCode);
		data.put("blockCode", blockCode);
		data.put("villageCode", villageCode);
		data.put("townCode", townCode);
		data.put("wardCode", wardCode);

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
