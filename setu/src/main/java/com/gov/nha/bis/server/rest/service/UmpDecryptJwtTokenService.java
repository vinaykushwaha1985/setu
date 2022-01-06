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
public class UmpDecryptJwtTokenService {


	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	
	private static final Logger logger = LogManager.getLogger(UmpDecryptJwtTokenService.class);
	
	public String decryptJwtToken(String token) {

		String res=requestDecryptJwtToken(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.UMP_DECRYPT_JWT_CLIENT_ID,
				applicationConstantConfig.UMP_DECRYPT_JWT_CLIENT_SECRET,
				applicationConstantConfig.UMP_DECRYPT_JWT_TOKEN_URL),
				getRequest(token), applicationConstantConfig.UMP_DECRYPT_JWT_URL);
		return res;
	}
	
	public String getRequest(String token) {
		
		JSONObject request= new JSONObject();
		request.put("token", token);
		
		
		return request.toString();
		
	}
	
	public   String requestDecryptJwtToken(String sAccessToken,String requestJson,String url){
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
