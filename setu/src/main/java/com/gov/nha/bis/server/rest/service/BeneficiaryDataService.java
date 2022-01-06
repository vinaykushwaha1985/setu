package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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
import com.gov.nha.bis.server.model.RuralEntity;

public class BeneficiaryDataService {

	private static final Logger logger = LogManager.getLogger(BeneficiaryDataService.class);

	public static List<RuralEntity> getRuralVillList(String stateCode, String districtCode,String blockCode,
			String villageCode,String urFlag,String url){

		String strData=new BeneficiaryDataService().getVillData(getRuralRequestJson(stateCode, districtCode, blockCode, villageCode, urFlag),url);

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


	public static List<RuralEntity> getUrbanVillList(String stateCode, String districtCode,String wardCode,
			String townCode,String urFlag,String url){

		String strData=new BeneficiaryDataService().getVillData(getUrbanRequestJson(stateCode, districtCode, wardCode, townCode, urFlag),url);

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


	public static String getRuralRequestJson(String stateCode, String districtCode,String blockCode,
			String villageCode,String urFlag){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);

		data.put("districtCode", districtCode);
		data.put("blockCode", blockCode);
		data.put("villageCode", villageCode);
		data.put("urFlag", urFlag);
		logger.info(data.toString());
		return data.toString();
	}

	public static String getUrbanRequestJson(String stateCode, String districtCode,String wardCode,
			String townCode,String urFlag){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("wardCode", wardCode);
		data.put("townCode", townCode);
		data.put("urFlag", urFlag);

		return data.toString();
	}




}
