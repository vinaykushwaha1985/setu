/**
 * 
 */
package com.gov.nha.bis.server.rest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.SeccParam;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Service
public class SaveSeccFamilyDataService {


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(SaveKycDataService.class);

	public String saveSecc(SeccParam seccParam) {


		Gson gson = new Gson();
	
		return getSave(gson.toJson(seccParam), applicationConstantConfig.SECC_ADD_FAMILY_DATA_URL);
	}



	public  String getSave(String requestJson,String url){
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
