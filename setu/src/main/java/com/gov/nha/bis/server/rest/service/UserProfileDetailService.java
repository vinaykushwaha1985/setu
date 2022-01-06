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
public class UserProfileDetailService {

	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	@Autowired
	public NhaBisCardAccessTokenService nhaBisCardAccessTokenService;
	
	
	private static final Logger logger = LogManager.getLogger(UserProfileDetailService	.class);
	
	public String authUserExist(String mobile,String applicationType) {
		
		
		String res=authUserRequest(nhaBisCardAccessTokenService.getTokenOauth2_0(applicationConstantConfig.NHA_BIS_PROFILE_DETAIL_CLIENT_ID,
				applicationConstantConfig.NHA_BIS_PROFILE_DETAIL_CLIENT_SECRET,
				applicationConstantConfig.NHA_BIS_PROFILE_DETAIL_TOKEN_URL),
				getRequest(mobile), applicationConstantConfig.NHA_BIS_PROFILE_DETAIL_URL);
		
		
		return res;
		
	}
	
	public String getRequest(String mobile) {
		
		JSONObject request= new JSONObject();
		request.put("loginId", mobile);
		request.put("loginMode", applicationConstantConfig.NHA_BIS_APPLICATION_LOGIN_MODE);
		request.put("applicationType", "BIS2.0");
		
		return request.toString();
		
	}
	
	public   String authUserRequest(String sAccessToken,String requestJson,String url){
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
