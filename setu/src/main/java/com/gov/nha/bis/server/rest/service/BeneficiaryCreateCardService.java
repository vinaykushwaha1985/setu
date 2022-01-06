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
import com.gov.nha.bis.server.dto.PushCardDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class BeneficiaryCreateCardService {
	
	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(BeneficiaryCreateCardService.class);
	
	public String pushCardRequestToBis(PushCardDto cardDto) {
		
		Gson gson = new Gson();
		
		String res=pushBenificiaryDetails(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_CREATE_CARD_Client_ID,
				applicationConstantConfig.NHA_BIS_CREATE_CARD_Client_Secret,
				applicationConstantConfig.NHA_BIS_CREATE_CARD_Token_URL),
				gson.toJson(cardDto), applicationConstantConfig.NHA_BIS_CREATE_CARD_URL);
		
		
		return res;
		
	}
	
	public String updateDemoKcyUidToken(String guid,String stateCode,String uidToken) {
		
		String res=pushBenificiaryDetails(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_DEMO_UPDATE_UIDTOKEN_CLIENT_ID,
				applicationConstantConfig.NHA_BIS_DEMO_UPDATE_UIDTOKEN_CLIENT_SECRET,
				applicationConstantConfig.NHA_BIS_DEMO_UPDATE_UIDTOKEN_TOKEN_URL),
				getUidTokenRequest(guid,stateCode,uidToken), applicationConstantConfig.NHA_BIS_DEMO_UPDATE_UIDTOKEN_URL);
		
		
		return res;
		
	}
	
	public String getUidTokenRequest(String guid,String stateCode,String uidToken) {
	    	
	    JSONObject request= new JSONObject();
		request.put("operationType", applicationConstantConfig.NHA_BIS_OPERATION_TYPE);
		request.put("guid", guid);
		request.put("stateCode", stateCode);
		request.put("uidToken", uidToken);
		
		return request.toString();
	    
	}
	
	public   String pushBenificiaryDetails(String sAccessToken,String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			logger.info("requestJson===="+requestJson);
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
