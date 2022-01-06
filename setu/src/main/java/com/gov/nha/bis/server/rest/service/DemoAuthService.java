package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoAuthService {
	
	private static final Logger logger = LogManager.getLogger(DemoAuthService.class);
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public String demoAuth(String aadhaar,String name,String gender,String dob,String mobile,String pincode,String guardian,String url) {
		
		String response="";
		DemoAuthService demoAuthService= new DemoAuthService();
		
		response=demoAuthService.getDemoAuth(getDemoRequestJson(aadhaar, name, gender, dob, mobile,  pincode, guardian), url);
		
		return response;
	}
	
	
	public static String getDemoRequestJson(String aadhaar,String name,String gender,String dob,String mobile,
			String pincode,String guardian){

		JSONObject data= new JSONObject();
		data.put("aadhaar", aadhaar);
		data.put("name", name);
		data.put("gender", gender);
		data.put("dob", dob);
		
		/*
		 * try { data.put("dob", formatter1.format(formatter.parse(dob))); } catch
		 * (JSONException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	
	 if(!ObjectUtils.isEmpty(mobile))
		data.put("mobile", mobile);
	
	 if(!ObjectUtils.isEmpty(pincode))
		 data.put("pincode", pincode);
	 
	 if(!ObjectUtils.isEmpty(guardian))
		 data.put("guardian", guardian);	

		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getDemoAuth(String requestJson,String url){
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
