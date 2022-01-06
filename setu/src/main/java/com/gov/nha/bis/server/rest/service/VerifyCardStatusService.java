package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.List;

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
public class VerifyCardStatusService {
	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(UpdateCardStatusService.class);

	
	
public  String verifyCardStatus(String stateCode,List<String> guid) {
		
		String response="";
		
		response=verifyCardStatusDetails(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_GUID_VERIFY_STATUS_CLIENT_ID,
						applicationConstantConfig.NHA_BIS_GUID_VERIFY_STATUS_CLIENT_SECRET,
						applicationConstantConfig.NHA_BIS_GUID_VERIFY_STATUS_TOKEN_URL)
				, getuidTokenRequestJson(stateCode, guid,applicationConstantConfig.NHA_BIS_OPERATION_TYPE), 
				applicationConstantConfig.NHA_BIS_GUID_VERIFY_STATUS_URL);
				
		return response;
	}


	private String getuidTokenRequestJson(String stateCode, List<String> guid, String operationType) {
		
		JSONObject data= new JSONObject();
		
		data.put("stateCode", stateCode);
		data.put("guidList", guid);
		data.put("operationType", operationType);
		
		logger.info(data.toString());
		return data.toString();
	}

	
	public   String verifyCardStatusDetails(String sAccessToken,String requestJson,String url){
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
