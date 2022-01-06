package com.gov.nha.bis.server.rest.service;

import java.math.BigInteger;

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
public class AppHistoryService {

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(SmsOtpService2.class);

	
	
	public  String saveAadharAuthDetails(int state_code,String  district_code ,String uidenc,BigInteger guid ,
			String userid,String authtype ,String status,String errorcode,
			int schemeid,String rural_urban,String apptype) {

		String response="";

		response=sendSms(createAuthRequest(state_code,  district_code , uidenc, guid , userid,
				authtype , status, errorcode, schemeid, rural_urban, apptype), applicationConstantConfig.APP_AADHAAR_AUTH_HISTORY_URL);

		return response;
	}
	
	
	/**
	 * @param state_code
	 * @param district_code
	 * @param uidenc
	 * @param guid
	 * @param userid
	 * @param authtype
	 * @param status
	 * @param errorcode
	 * @param schemeid
	 * @param rural_urban
	 * @param apptype
	 * @return
	 */
	private String createAuthRequest(int state_code, String district_code, String uidenc, BigInteger guid,
			String userid, String authtype, String status, String errorcode, int schemeid, String rural_urban,
			String apptype) {
		// TODO Auto-generated method stub
		JSONObject data= new JSONObject();
		data.put("state_code", state_code);
		data.put("district_code", district_code);
		
		data.put("uidenc", uidenc);
		data.put("apptype", apptype);
		data.put("guid", guid);
		data.put("userid", userid);
		
		
		data.put("authtype", authtype);
		data.put("status", status);
		data.put("errorcode", errorcode);
		data.put("schemeid", schemeid);
		data.put("rural_urban", rural_urban);
		data.put("apptype", apptype);
		
		
		
		logger.info(data.toString());
		
		return data.toString();
	}


	public  String saveLoginDetails(String userid,String mobile,String roleid,String apptype,String ip,String name) {

		String response="";

		response=sendSms(sendLoginRequest( userid, mobile, roleid, apptype, ip, name),applicationConstantConfig.LOGIN_HISTORY_URL);

		return response;
	}

	public  String saveEventDetails(int state_code,String   district_code,String rural_urban,
			String  userid,String  mobile,String apptype,String roleId,String   eventtype,String  ip,String name) {

		String response="";

		response=sendSms(sendEventRequest( state_code,   district_code, rural_urban,
				  userid,  mobile, apptype,roleId,   eventtype,  ip,name), applicationConstantConfig.EVENT_HISTORY_URL);

		return response;
	}


	public  String sendLoginRequest(String userid,String mobile,String roleid,String apptype,String ip,String name){

		JSONObject data= new JSONObject();
		data.put("mobile", mobile);
		data.put("userid", userid);
		
		data.put("roleid", roleid);
		data.put("apptype", apptype);
		data.put("ip", ip);
		data.put("name", name);
		
		logger.info(data.toString());
		return data.toString();
	}

	
	
	public  String sendEventRequest(int state_code,String   district_code,String rural_urban,
			String  userid,String  mobile,String apptype,String roleId,String   eventtype,String  ip,String name){

		JSONObject data= new JSONObject();
		data.put("mobile", mobile);
		data.put("userid", userid);
		data.put("apptype", apptype);
		
		data.put("eventtype", eventtype);
		data.put("ip", ip);
		data.put("state_code", state_code);
		data.put("district_code", district_code);
		data.put("rural_urban", rural_urban);
		data.put("roleid", roleId);
		data.put("name", name);
		
		logger.info(data.toString());
		return data.toString();
	}


	public   String sendSms(String requestJson,String url){
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
