package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.gov.nha.bis.server.config.token.NhaBisMobileNumberCheckService;
import com.gov.nha.bis.server.dto.CheckMobileNumberDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;


@Service
public class MobileNumberCheckService {
	
	@Autowired
	public NhaBisMobileNumberCheckService nhaBisGetMobileNumberCheckService;
	
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	

	private static final Logger logger = LogManager.getLogger(CardDownloadService.class);
	
	public String checkMobileNumber( CheckMobileNumberDto mobilenumberDto) {

	Gson gson = new Gson();
	String Token = nhaBisGetMobileNumberCheckService.getMobileNumberOauth2_0(applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_ID, applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_Client_Secret, applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_Token_URL);
	
	logger.info(Token);
	String response=checkMobileNumber(Token,gson.toJson(mobilenumberDto), applicationConstantConfig.NHA_BIS_CHECK_MOBILE_NUMBER_URL);
	
	return response;
	
	}

	public   String checkMobileNumber(String sAccessToken,String requestJson,String url){
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
