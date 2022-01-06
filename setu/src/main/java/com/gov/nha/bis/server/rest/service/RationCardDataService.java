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

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.RationParam;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;

@Service
public class RationCardDataService {


	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(RationCardDataService.class);

	public String saveRationData(RationParam rPram) {

		String response="";
		Gson gson = new Gson();
		if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.SEARCH_SERVICE_REDIS_ENABLE_LIST.split(",")).contains(rPram.getState_lgd_code())) {
			response=getSaveDemo(gson.toJson(rPram), applicationConstantConfig.REDIS_RATION_CARD_DATA_SAVE_URL);
		}else {	
			response=getSaveDemo(gson.toJson(rPram), applicationConstantConfig.RATION_CARD_DATA_SAVE_URL);
		}
		return response;
	}



	public String fetchRationData(String refernceid,String stateCode,String ruflag,int status) {

		String response="";
		if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.SEARCH_SERVICE_REDIS_ENABLE_LIST.split(",")).contains(stateCode)) {
			response=getSaveDemo(fetchRequestJson(refernceid, stateCode, ruflag,
					status), applicationConstantConfig.REDIS_RATION_CARD_DATA_FETCH_URL);
		}else {	
		response=getSaveDemo(fetchRequestJson(refernceid, stateCode, ruflag,
				status), applicationConstantConfig.RATION_CARD_DATA_FETCH_URL);
		}
		return response;
	}


	private String fetchRequestJson(String refernceid, String stateCode, String ruflag, int status) {
		// TODO Auto-generated method stub
		JSONObject data= new JSONObject();
		data.put("refernceid", refernceid);
		data.put("statecode", stateCode);
		data.put("ruflag", ruflag);
		data.put("status", status);


		logger.info(data.toString());
		return data.toString();
	}


	public  String getSaveRequestJson(int state_lgd_code, int district_lgd_code,int subdistrict_lgd_code,
			int village_town_lgd_code, String pincode,String rural_urban, String family_id,Long guid,String family_mem_id,
			String member_name_eng, String mother_name_eng,String father_name_eng, String spouse_name_eng, 
			int year_of_birth,
			String relation_name, String gender,String creationby, int status){

		JSONObject data= new JSONObject();
		data.put("state_lgd_code", state_lgd_code);
		data.put("district_lgd_code", district_lgd_code);
		data.put("subdistrict_lgd_code", subdistrict_lgd_code);
		data.put("village_town_lgd_code", village_town_lgd_code);
		data.put("pincode", pincode);
		data.put("rural_urban", rural_urban); 
		data.put("family_id", family_id);
		data.put("guid", guid);
		data.put("family_mem_id", family_mem_id);
		data.put("member_name_eng", member_name_eng);


		data.put("mother_name_eng", mother_name_eng);
		data.put("father_name_eng", father_name_eng);
		data.put("spouse_name_eng", spouse_name_eng);
		data.put("year_of_birth", year_of_birth);
		data.put("relation_name", relation_name);
		data.put("gender", gender);
		data.put("creationby", creationby);
		data.put("status", status);


		logger.info(data.toString());
		return data.toString();
	}


	public  String getSaveDemo(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{
logger.info("Ration Card Save requestJson==="+requestJson);
logger.info("Ration Card Save url==="+url);
			
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
