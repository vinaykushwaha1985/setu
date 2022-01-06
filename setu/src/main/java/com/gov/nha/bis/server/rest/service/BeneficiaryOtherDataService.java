package com.gov.nha.bis.server.rest.service;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.nha.bis.server.model.SchemeOther;

public class BeneficiaryOtherDataService {
	private static final Logger logger = LogManager.getLogger(BeneficiaryOtherDataService.class);
	
	public static List<SchemeOther> getRuralVillList(String stateCode, String districtCode,String urFlag,
			String schemeCode,String familyId,String url){

		String strData=new BeneficiaryDataService().getVillData(getRuralRequestJson(stateCode, districtCode, urFlag, schemeCode, familyId),url);

		//System.out.println(strData);

		List<SchemeOther> villageList = new ArrayList<SchemeOther>();

		if(!ObjectUtils.isEmpty(strData)) {
			JSONObject obj= new JSONObject(strData);
			
			System.out.println(obj.getBoolean("status"));
			if(obj.getBoolean("status")){
				JSONArray content = obj.getJSONArray("family");

				Reader reader = new InputStreamReader(new ByteArrayInputStream(content.toString().getBytes()));
				GsonBuilder gsonBuilder = new GsonBuilder();

				Gson gson = gsonBuilder.create();

				villageList = Arrays.asList(gson.fromJson(reader, SchemeOther[].class));

			}
		}

		return villageList;
	}




	@Override
	public String toString() {
		return "LoadDataService [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	} 
	public  String getVillData(String requestJson,String url){
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
	public static String getRuralRequestJson(String stateCode, String districtCode,String urFlag,
			String schemeCode,String familyId){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);

		data.put("districtCode", districtCode);
		data.put("rural_urban", urFlag);
		data.put("schemeCode",schemeCode);
		data.put("familyid",familyId);
		logger.info(data.toString());
		return data.toString();
	}
}
