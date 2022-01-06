package com.gov.nha.bis.server.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

public class RationCardUtil {

	
	/*
	 * public static void main(String[] args) {
	 * 
	 * String res= sendReq(RationCardUtil.reqJson("2","011000000001","01","Y",
	 * getAccessCode(sendReq(accessCode("01",
	 * "NXMxcnZhVXpBdGY4T20vV3BuMWNPZz09","TThzNk1Xd2lGZFVIQlEvZHJUT1pKZz09",
	 * "emk1TWNManVpNHNEZnVOWnZJd3RVeFpaVyt4YW5aYURJY3JGc3F3WHd6bz06MDAwMDAwMDAwMDAwMTU0OjYzNzU3Mjg5NzI0ODc5NTcwNA=="
	 * ), "https://apis-prd.pmjay.gov.in/api/PMJAY/pmjay_get_accesstoken")),getCode(
	 * "01", "000","00002" )),
	 * "https://apis-prd.pmjay.gov.in/PMJAY/JK/getPDSDetail");
	 * 
	 * System.out.println(res); }
	 */
	
	
	public static String getAccessCode(String reString) {
	
		String accessToken=null;
		if(!ObjectUtils.isEmpty(reString)) {
		JSONObject req= new JSONObject(reString);

			 accessToken=req.getString("AccessToken");
		}
		return accessToken;
	}

public static String getCode(String stateCode, String val1,String val2 ) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = sdf.format(cal.getTime());
		System.out.println("Current date in String Format: " + strDate);

		String dateStr=	strDate.replaceAll("/", "").replaceAll(" ", "").replaceAll(":", "");
		
	return stateCode+val1+dateStr+val2;
	}




	public static String reqJson(String idType,String rationno,String stateCode,String consent, String accessToken,String token) {
		JSONObject req= new JSONObject();

		req.put("ID_Type", idType);
		req.put("ID_Number", rationno);
		req.put("Token", token);
		req.put("state_code", stateCode);
		req.put("Beneficiary_Consent", consent);
		req.put("access_token", accessToken);

		return req.toString();
	}


	public static String accessCode(String state,String userId,String password,String authStr) {
		JSONObject req= new JSONObject();
		req.put("State", state);
		req.put("UserId", userId);
		req.put("Password", password);
		req.put("AuthString", authStr);

		return req.toString();
	}
	
	public static String sendReq(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			System.out.println("requestJson"+requestJson);
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
