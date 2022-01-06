package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class OtpUidaiAuthService {


	
	private static final Logger logger = LogManager.getLogger(OtpUidaiAuthService.class);
	
	public static String otpUidaiAuth(String aadhaar,String otp,String txn,String url) {
		
		String response="";
		
		response=getOtpUidaiAuth(getOtpUidaiAuthRequestJson(aadhaar, otp, txn), url);
		
		return response;
	}
	
	
	public static String getOtpUidaiAuthRequestJson(String aadhaar,String otp,String txn){

		JSONObject data= new JSONObject();
		data.put("aadhaar", aadhaar);
		data.put("otp", otp);
		data.put("txn", txn);
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  static String getOtpUidaiAuth(String requestJson,String url){
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
