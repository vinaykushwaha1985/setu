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
public class UmpJwtTokenService {

	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	
	private static final Logger logger = LogManager.getLogger(UmpJwtTokenService.class);
	
	public String jwtToken(String mobile,String authMode) {
		
		
		String res=requestJwtToken(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.UMP_USER_REG_JWT_CLIENT_ID,
				applicationConstantConfig.UMP_USER_REG_JWT_CLIENT_SECRET,
				applicationConstantConfig.UMP_USER_REG_JWT_TOKEN_URL),
				getRequest(mobile,authMode), applicationConstantConfig.UMP_USER_REG_JWT_URL);
		
		
		return res;
		
	}
	
	public String getRequest(String mobile,String authMode) {
		
		JSONObject request= new JSONObject();
		request.put("mobileNo", mobile);
		request.put("roleId", "");
		request.put("appType", applicationConstantConfig.NHA_BIS_APPLICATION_TYPE);
		request.put("authMode", authMode);
		
		return request.toString();
		
	}
	
	public   String requestJwtToken(String sAccessToken,String requestJson,String url){
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
