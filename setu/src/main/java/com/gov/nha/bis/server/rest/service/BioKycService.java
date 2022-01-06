package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BioKycService {


	
	private static final Logger logger = LogManager.getLogger(BioKycService.class);
	
	public String bioKyc(String aadhaar,String pidData,String bioType,String url) {
		
		String response="";
		
		response=getBioKyc(getBioKycRequestJson(aadhaar, pidData,bioType), url);
		
		return response;
	}
	
	
	public String bioAuth(String aadhaar,String pidData,String bioType,String url) {
		
		String response="";
		
		response=getBioKyc(getBioAuthRequestJson(aadhaar, pidData,bioType), url);
		
		return response;
	}
	
	public static String getBioKycRequestJson(String aadhaar,String pidData,String bioType){

		JSONObject data= new JSONObject();
		data.put("aadhaar", aadhaar);
		
		data.put("consent", "Y");
		data.put("bioAuthType",bioType);
		if(bioType.equalsIgnoreCase("F")) { 
			data.put("bioType","FMR");
			data.put("pidData", new String(Base64.encode(pidData.getBytes())));
		}else if(bioType.equalsIgnoreCase("I")) {
		data.put("bioType","IIR");
		data.put("pidData", new String(Base64.encode(pidData.getBytes())));
		}else if(bioType.equalsIgnoreCase("P")) {
			data.put("bioType","FID");
			data.put("pidData",pidData);
		}
		logger.info(data.toString());
		return data.toString();
	}
	

	public static String getBioAuthRequestJson(String aadhaar,String pidData,String bioType){

		JSONObject data= new JSONObject();
		data.put("aadhaar", aadhaar);
		
		data.put("bioAuthType",bioType);
		if(bioType.equalsIgnoreCase("F")) { 
			data.put("bioType","FMR");
			data.put("pidData", new String(Base64.encode(pidData.getBytes())));
		}else if(bioType.equalsIgnoreCase("I")) {
		data.put("bioType","IIR");
		data.put("pidData", new String(Base64.encode(pidData.getBytes())));
		}else if(bioType.equalsIgnoreCase("P")) {
			data.put("bioType","FID");
			data.put("pidData",pidData);
		}
		logger.info(data.toString());
		return data.toString();
	}

	
	public  String getBioKyc(String requestJson,String url){
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
