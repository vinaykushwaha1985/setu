package com.gov.nha.bis.server.rest.service;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.nha.bis.server.model.StateScheme;

public class SchemeDataService {
	private static final Logger logger = LogManager.getLogger(SchemeDataService.class);

	public static List<StateScheme> getStateSchemeList(String stateCode,String url){

		HashMap<String,String> stateMap = new HashMap<String,String>();
		List<StateScheme> schemeList = new ArrayList<StateScheme>();

		String strData=null;
		SchemeDataService schemeDataService=	new SchemeDataService();
			strData=schemeDataService.getStateSchemeData(getSchemeRequestJson(stateCode),url);
			JSONObject obj= new JSONObject(strData);
			if(obj.getString("status").equalsIgnoreCase("SUCCESS")) {
				JSONArray content = obj.getJSONArray("data");
				Reader reader = new InputStreamReader(new ByteArrayInputStream(content.toString().getBytes()));
				GsonBuilder gsonBuilder = new GsonBuilder();

				Gson gson = gsonBuilder.create();

				schemeList = Arrays.asList(gson.fromJson(reader, StateScheme[].class));
//				for (Object objData : content) {
//					JSONObject jsonLineItem = (JSONObject) objData;
//					if(jsonLineItem.has("schemeid"))
//					stateMap.put(jsonLineItem.getString("schemename").trim(),String.valueOf(jsonLineItem.getBigInteger("schemeid")));
//				}  
				//stateMap.remove(1);
		}
		logger.info(strData);


		return schemeList;
	}
	public static String getSchemeRequestJson(String stateCode){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);

		return data.toString();
	}
	public  String getStateSchemeData(String requestJson,String url){
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
	public static HashMap<String,String> getStateSubSchemeList(String stateCode,String schemeId, String url){

		HashMap<String,String> stateMap = new HashMap<String,String>();


		String strData=null;
		SchemeDataService schemeDataService=	new SchemeDataService();
			strData=schemeDataService.getStateSubSchemeData(getSubSchemeRequestJson(stateCode,schemeId),url);
			JSONObject obj= new JSONObject(strData);
			if(obj.getString("status").equalsIgnoreCase("SUCCESS")) {
				JSONArray content = obj.getJSONArray("data");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("subschemeid"))
					stateMap.put(jsonLineItem.getString("subschemename").trim(),String.valueOf(jsonLineItem.getString("subschemeid")));
				}  
				//stateMap.remove(1);
		}
		logger.info(strData);


		return stateMap;
	}
	
	
	
	public static String getSubSchemeRequestJson(String stateCode,String schemeId){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("schemeId", schemeId);

		return data.toString();
	}
	
	
	public  String getStateSubSchemeData(String requestJson,String url){
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

	public static HashMap<String,String> getStateSubSchemeDocumentTypeList(String subSchemeId,String schemeId, String url){

		HashMap<String,String> stateMap = new HashMap<String,String>();


		String strData=null;
		SchemeDataService schemeDataService=	new SchemeDataService();
			strData=schemeDataService.getStateSubSchemeSocumentTypeData(getSubSchemeDocumentTypeRequestJson(subSchemeId,schemeId),url);
			JSONObject obj= new JSONObject(strData);
			if(obj.getString("status").equalsIgnoreCase("SUCCESS")) {
				JSONArray content = obj.getJSONArray("data");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("documentid"))
					stateMap.put(jsonLineItem.getString("documentname").trim(),String.valueOf(jsonLineItem.getBigInteger("documentid")));
				}  
				//stateMap.remove(1);
		}
		logger.info(strData);


		return stateMap;
	}
	
	
	public static String getSubSchemeDocumentTypeRequestJson(String subSchemeId,String schemeId){

		JSONObject data= new JSONObject();
		data.put("subSchemeId", subSchemeId);
		data.put("schemeId", schemeId);

		return data.toString();
	}
	public  String getStateSubSchemeSocumentTypeData(String requestJson,String url){
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
