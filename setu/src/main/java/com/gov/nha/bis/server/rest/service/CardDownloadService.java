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
import com.gov.nha.bis.server.config.token.NhaBisCardAccessTokenService;
import com.gov.nha.bis.server.model.UserForm;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class CardDownloadService {

	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(CardDownloadService.class);

	public  String cardDownload(String stateCode,String pmjayId,String ahlTin,String appName,String url) {

		String response="";

		response=getCardDownloadDetails(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_CARD_Client_ID,
				applicationConstantConfig.NHA_BIS_CARD_Client_Secret,
				applicationConstantConfig.NHA_BIS_CARD_Token_URL)
				, getCardDownloadRequestJson(stateCode, pmjayId, ahlTin, appName), url);

		return response;
	}


	public  String getCardDownloadRequestJson(String stateCode,String pmjayId,String ahlTin,String appName){

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("pmjayId", pmjayId);
		data.put("ahlTin", ahlTin);
		data.put("appName", appName);

		//data.put("stateCode", "4");
		//data.put("pmjayId", "PG56M091S");


		logger.info(data.toString());
		return data.toString();
	}


	public   String getCardDownloadDetails(String sAccessToken,String requestJson,String url){
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



	public  String getUidToken(String stateCode,String pmjayId,String url) {

		String response="";

		response=getCardDownloadDetails(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_UID_TOKEN_Client_ID,
				applicationConstantConfig.NHA_BIS_UID_TOKEN_Client_Secret,
				applicationConstantConfig.NHA_BIS_UID_TOKEN_Token_URL)
				, getuidTokenRequestJson(stateCode, pmjayId,applicationConstantConfig.NHA_BIS_OPERATION_TYPE), url);

		return response;
	}


	private String getuidTokenRequestJson(String stateCode, String pmjayId, String operationType) {

		JSONObject data= new JSONObject();
		data.put("stateCode", stateCode);
		data.put("guid", pmjayId);
		data.put("operationType", operationType);

		logger.info(data.toString());
		return data.toString();
	}

}
