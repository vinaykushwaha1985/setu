package com.gov.nha.bis.server.rest.service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class GenerateGuidPmjayIdService {


	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(GenerateGuidPmjayIdService.class);
	
	public String getGuidPmjayId(String aadhaar,String memberId,String nfsaid,String other,String userId) {
		
		String response="";
		
		
		
		
		response=getGuid(getRequestJson(aadhaar, memberId, nfsaid, other, userId), 
				applicationConstantConfig.GEN_GUID_PMJAYID_URL);
		
		return response;
	}
	
	
	public static String getRequestJson(String aadhaar,String memberId,String nfsaid,String other,String userId){

		JSONObject data= new JSONObject();
		
		
		data.put("aadhaar", aadhaar);
		data.put("memberId", memberId);
		data.put("nsfaid", nfsaid);
		data.put("other", other);
		data.put("userId", userId);
		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getGuid(String requestJson,String url){
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
