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

import com.gov.nha.bis.server.config.token.NhaBisCardAccessTokenService;
import com.gov.nha.bis.server.global.function.StateApiTokenFunction;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.util.RationCardUtil;

@Service
public class RationCardStateService {

	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(RationCardStateService.class);

	public  String rationCardDetails(int stateCode,String rationCard) {

	
		switch(stateCode) {
		
		case 24:
			return sendRequest(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.STATE_24_GATEWAY_API_CLIENT_ID,
					applicationConstantConfig.STATE_24_GATEWAY_API_CLIENT_ID_SECRET,
					applicationConstantConfig.STATE_24_GATEWAY_API_TOKEN_URL)
					, geRequestJson(stateCode, rationCard, StateApiTokenFunction.getStateToken(stateCode, 
							applicationConstantConfig.STATE_24_TOKEN), 
							applicationConstantConfig.STATE_24_ID_TYPE), applicationConstantConfig.STATE_24_RATION_CARD_URL);
			
		case 9:
			return sendRequest(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.STATE_9_GATEWAY_API_CLIENT_ID,
					applicationConstantConfig.STATE_9_GATEWAY_API_CLIENT_ID_SECRET,
					applicationConstantConfig.STATE_9_GATEWAY_API_TOKEN_URL)
					, geRequestJson(stateCode, rationCard, StateApiTokenFunction.getStateToken(stateCode, 
							applicationConstantConfig.STATE_9_TOKEN), 
							applicationConstantConfig.STATE_9_ID_TYPE), applicationConstantConfig.STATE_9_RATION_CARD_URL);

		case 1:
			return sendRequestType2(RationCardUtil.reqJson(applicationConstantConfig.STATE_1_ID_TYPE,
					rationCard,(String.valueOf(stateCode).length()==1?("0"+stateCode):String.valueOf(stateCode)),
					applicationConstantConfig.STATE_1_RATION_CARD_CONSENT, RationCardUtil.getAccessCode(
							sendRequestType2(RationCardUtil.accessCode(applicationConstantConfig.STATE_1_ACCESS_STATE,
									applicationConstantConfig.STATE_1_ACCESS_USERID,applicationConstantConfig.STATE_1_ACCESS_PASSWORD,
									applicationConstantConfig.STATE_1_ACCESS_AUTHSTRING),
									applicationConstantConfig.STATE_1_ACCESS_CODE_URL)),RationCardUtil.getCode((String.valueOf(stateCode).length()==1?("0"+stateCode):String.valueOf(stateCode)),
											applicationConstantConfig.STATE_1_RATION_CARD_ACCESS_VAL1,applicationConstantConfig.STATE_1_RATION_CARD_ACCESS_VAL2 )),
					applicationConstantConfig.STATE_1_RATION_CARD_URL);
			
			
		case 6:
			return sendRequestType2(RationCardUtil.reqJson(applicationConstantConfig.STATE_6_ID_TYPE,
					rationCard,(String.valueOf(stateCode).length()==1?("0"+stateCode):String.valueOf(stateCode)),
					applicationConstantConfig.STATE_6_RATION_CARD_CONSENT, RationCardUtil.getAccessCode(
							sendRequestType2(RationCardUtil.accessCode(applicationConstantConfig.STATE_6_ACCESS_STATE,
									applicationConstantConfig.STATE_6_ACCESS_USERID,
									applicationConstantConfig.STATE_6_ACCESS_PASSWORD,
									applicationConstantConfig.STATE_6_ACCESS_AUTHSTRING),
									applicationConstantConfig.STATE_6_ACCESS_CODE_URL)),
					RationCardUtil.getCode((String.valueOf(stateCode).length()==1?("0"+stateCode):String.valueOf(stateCode)),
											applicationConstantConfig.STATE_6_RATION_CARD_ACCESS_VAL1,
											applicationConstantConfig.STATE_6_RATION_CARD_ACCESS_VAL2 )),
					applicationConstantConfig.STATE_6_RATION_CARD_URL);

		case 18:
			return sendRequestType2(RationCardUtil.reqJson(applicationConstantConfig.STATE_18_ID_TYPE,
					rationCard,(String.valueOf(stateCode).length()==1?("0"+stateCode):String.valueOf(stateCode)),
					applicationConstantConfig.STATE_18_RATION_CARD_CONSENT, RationCardUtil.getAccessCode(
							sendRequestType2(RationCardUtil.accessCode(applicationConstantConfig.STATE_18_ACCESS_STATE,
									applicationConstantConfig.STATE_18_ACCESS_USERID,
									applicationConstantConfig.STATE_18_ACCESS_PASSWORD,
									applicationConstantConfig.STATE_18_ACCESS_AUTHSTRING),
									applicationConstantConfig.STATE_18_ACCESS_CODE_URL)),
									RationCardUtil.getCode((String.valueOf(stateCode).length()==1?("0"+stateCode):String.valueOf(stateCode)),
											applicationConstantConfig.STATE_18_RATION_CARD_ACCESS_VAL1,
											applicationConstantConfig.STATE_18_RATION_CARD_ACCESS_VAL2 )),
					applicationConstantConfig.STATE_18_RATION_CARD_URL);
		}
		
		return null;
	}


	public  String geRequestJson(int stateCode,String rationCard,String token,String idType){

		JSONObject data= new JSONObject();
		data.put("state_code", stateCode);
		data.put("ID_Number", rationCard);
		data.put("Token", token);
		data.put("ID_Type", idType);

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
	
	public static String sendRequestType2(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			System.out.println("requestJson"+requestJson);
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
