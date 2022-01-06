package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class MasterDataService {

	private static final Logger logger = LogManager.getLogger(MasterDataService.class);

	public static HashMap<String,String> getStateList(String stateCode, String districtCode,String blockCode,
			String villageCode,String townCode,String type,String url){

		HashMap<String,String> stateMap = new HashMap<String,String>();


		String strData=null;
		MasterDataService stateDataService=	new MasterDataService();
		if(type.equalsIgnoreCase("S")){
			strData=stateDataService.getStateData(getStateRequestJson(type),url);
			
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("stateData");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("state_code"))
					stateMap.put(jsonLineItem.getString("state_name_english").trim(),String.valueOf(jsonLineItem.getBigInteger("state_code")));
				}  

			}
			
		}else if(type.equalsIgnoreCase("D")){
			strData=stateDataService.getStateData(getDistrictRequestJson(stateCode, type),url);
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("stateData");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("district_code"))
					stateMap.put(jsonLineItem.getString("district_name_english").trim(),String.valueOf(jsonLineItem.getBigInteger("district_code")));
				}  

			}

			
		}else if(type.equalsIgnoreCase("B")){
			strData=stateDataService.getStateData(getBlockRequestJson(stateCode, districtCode, type),url);
			
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("stateData");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("block_code"))
					stateMap.put(jsonLineItem.getString("block_name_english").trim(),String.valueOf(jsonLineItem.getInt("block_code")));
				}  

			}
			
		}else if(type.equalsIgnoreCase("V")){
			strData=stateDataService.getStateData(getVillageRequestJson(stateCode, districtCode, blockCode, type),url);
		
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("stateData");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("village_code"))
					stateMap.put( jsonLineItem.getString("village_name_english").trim(),String.valueOf(jsonLineItem.getInt("village_code")));
				}  

			}

			
		}	else if(type.equalsIgnoreCase("T")){
			strData=stateDataService.getStateData(getTownRequestJson(stateCode, districtCode, type),url);
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("stateData");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("town_code"))
					stateMap.put(jsonLineItem.getString("town_name"),jsonLineItem.getString("town_code"));
				}  

			}

			
		}else if(type.equalsIgnoreCase("W")){
			strData=stateDataService.getStateData(getWardRequestJson(stateCode, districtCode, townCode, type),url);
			JSONObject obj= new JSONObject(strData);

			if(Boolean.valueOf(obj.getString("status"))){
				JSONArray content = obj.getJSONArray("stateData");

				for (Object objData : content) {
					JSONObject jsonLineItem = (JSONObject) objData;
					if(jsonLineItem.has("ward_code"))
					stateMap.put(jsonLineItem.getString("ward_code"), jsonLineItem.getString("ward_name"));
				}  

			}
			
		}
		logger.info(strData);


		return stateMap;
	}




	@Override
	public String toString() {
		return "LoadDataService [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	} 




	public  String getStateData(String requestJson,String url){
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


	public static String getStateRequestJson(String type){

		JSONObject data= new JSONObject();
		data.put("type", type);

		return data.toString();
	}
	
	public static String getDistrictRequestJson(String stateCode,String type){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("type", type);

		return data.toString();
	}
	
	public static String getBlockRequestJson(String stateCode,String districtCode,String type){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("type", type);

		return data.toString();
	}
	
	public static String getVillageRequestJson(String stateCode,String districtCode,String blockCode,String type){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("blockCode", blockCode);
		data.put("type", type);

		return data.toString();
	}
	public static String getTownRequestJson(String stateCode,String districtCode,String type){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("type", type);
		return data.toString();
	}
	public static String getWardRequestJson(String stateCode,String districtCode,String townCode,String type){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("townCode", townCode);
		data.put("type", type);
		return data.toString();
	}

}
