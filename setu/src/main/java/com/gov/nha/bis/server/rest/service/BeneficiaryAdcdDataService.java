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
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.nha.bis.server.model.RuralEntity;

@Service
public class BeneficiaryAdcdDataService {

	private static final Logger logger = LogManager.getLogger(BeneficiaryDataService.class);

	public  List<RuralEntity> getRuralVillList(String stateCode, String districtCode,String ration,String mobile,String scheme, String urFlag,String url){

		String strData=getVillData(getRequestJson(stateCode, districtCode, ration, mobile,scheme, urFlag),url);

		//System.out.println(strData);

		List<RuralEntity> villageList = new ArrayList<RuralEntity>();

		if(!ObjectUtils.isEmpty(strData)) {
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("villageData");

				Reader reader = new InputStreamReader(new ByteArrayInputStream(content.toString().getBytes()));
				GsonBuilder gsonBuilder = new GsonBuilder();

				Gson gson = gsonBuilder.create();

				villageList = Arrays.asList(gson.fromJson(reader, RuralEntity[].class));

			}
		}

		return villageList;
	};


	
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


	public static String getRequestJson(String stateCode, String districtCode,String ration,String mobile,
			String scheme,String urFlag){

		JSONObject data= new JSONObject();
		data.put("state_code", stateCode);

		data.put("district_code", districtCode);
		//data.put("blockCode", districtCode);
		if(scheme.equalsIgnoreCase("4")) {
			data.put("rationcard_number", ration);
			data.put("search_type", "R");
		}else {
			data.put("mobile_number", mobile);
			data.put("search_type", "M");
		}

		data.put("rural_urban", urFlag);
		logger.info(data.toString());
		return data.toString();
	}

}
