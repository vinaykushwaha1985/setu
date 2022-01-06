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
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class PmjayIdService {

	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(CardDownloadService.class);

	
	
public  String getPmjayId(String stateCode,String pmjayId) {
		
		String response="";
		
		response=getPmajIdDetails(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_ID,
						applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_Client_Secret,
						applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_Token_URL)
				, getuidTokenRequestJson(stateCode, pmjayId,applicationConstantConfig.NHA_BIS_OPERATION_TYPE), applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_URL);
				
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



	
	public   String getPmajIdDetails(String sAccessToken,String requestJson,String url){
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
