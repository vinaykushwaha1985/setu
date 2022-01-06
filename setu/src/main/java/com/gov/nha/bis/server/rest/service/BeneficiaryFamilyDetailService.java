/**
 * 
 */
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.nha.bis.server.dto.RuralDto;
import com.gov.nha.bis.server.model.RuralEntity;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
public class BeneficiaryFamilyDetailService {
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(SaveKycDataService.class);

	public static List<RuralEntity> beneficiaryFamilyHhdData(String stateCode,String urFlag,String hhdid,String url) {

		String response=getHHDDeta(getRequestJson(stateCode,urFlag,hhdid), url);
		List<RuralEntity> villageList = new ArrayList<RuralEntity>();

		if(!ObjectUtils.isEmpty(response)) {

			JSONObject obj= new JSONObject(response);
			if(obj.getString("status").equalsIgnoreCase("SUCCESS")){
				
				JSONArray content = obj.getJSONArray("data");

				Reader reader = new InputStreamReader(new ByteArrayInputStream(content.toString().getBytes()));
				GsonBuilder gsonBuilder = new GsonBuilder();

				Gson gson = gsonBuilder.create();

				villageList = Arrays.asList(gson.fromJson(reader, RuralEntity[].class));

			}
		}
		return villageList;		
			
		
	}
	public static String getRequestJson(String stateCode,String urFlag,String hhdid){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("urFlag", urFlag);
		data.put("hhno", hhdid);

		logger.info(data.toString());
		return data.toString();
	}
	public static String getHHDDeta(String requestJson,String url){
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
