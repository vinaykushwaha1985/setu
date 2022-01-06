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

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class TxnSaveDataService {

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(TxnSaveDataService.class);
	
	public String saveDownCardStatus(String refernceid,String mobile,String aadhaar,String stateCode,String userid) {
		
		String response="";
		
		response=getSaveTxn(getTxnRequestJson(refernceid, mobile, aadhaar, stateCode, userid), applicationConstantConfig.TXN_DOWN_CARD_URL);
		
		return response;
	}
	
	
	public static String getTxnRequestJson(String refernceid,String mobile,String aadhaar,String stateCode,String userid){

		JSONObject data= new JSONObject();
		data.put("referenceid", refernceid);
		data.put("statecode", stateCode);
		data.put("mobile", mobile);
		data.put("aadhaar", aadhaar);
		data.put("userid", userid);
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getSaveTxn(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

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
