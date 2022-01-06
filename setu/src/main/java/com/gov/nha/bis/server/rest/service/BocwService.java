package com.gov.nha.bis.server.rest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.config.token.NhaBisCardAccessTokenService;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class BocwService {

	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(BocwService.class);

	public  String getDetails(int stateCode,String bocwId) {


		switch(stateCode) {

		case 9:
			return sendRequest(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.BOCW_STATE_9_GATEWAY_API_CLIENT_ID,
					applicationConstantConfig.BOCW_STATE_9_GATEWAY_API_CLIENT_ID_SECRET,
					applicationConstantConfig.BOCW_STATE_9_GATEWAY_API_TOKEN_URL)
					, geRequestJson(stateCode, bocwId, 
							applicationConstantConfig.BOCW_STATE_9_ID_TYPE), applicationConstantConfig.BOCW_STATE_9_URL);
		}

		return null;
	}


	public  String geRequestJson(int stateCode,String bocwId,String idType){

		JSONObject data= new JSONObject();
		data.put("state_code", stateCode);
		data.put("id_number", bocwId);
		data.put("id_type", idType);

		logger.info(data.toString());
		return data.toString();
	}


	public   String sendRequest(String sAccessToken,String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			logger.info("url===="+url);
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(sAccessToken);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(url, entity, String.class);
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return returnStr;
	}

}
