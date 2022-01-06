package com.gov.nha.bis.server.response;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.RuralDto;
import com.gov.nha.bis.server.dto.UrbanDto;

public class BeneficiaryServiceResponse {
	
	public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	

	public static String ruralListResponse(List<RuralDto> ruralList,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray villageArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
		sRturnResponse.put("villageData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("villageData",  villageArry);
		
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	
	public static String urbanListResponse(List<UrbanDto> ruralList,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray villageArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
		sRturnResponse.put("villageData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("villageData",  villageArry);
		
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	
	public static String updateBenStatusResponse(String guid,boolean status,String errorCode, String errDes,String rural_urban ) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("guid", guid);
		sRturnResponse.put("rural_urban", rural_urban);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	public static String ruralDtoResponse(RuralDto ruralDto,String guid,boolean status,String errorCode, String errDes,String rural_urban ) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralDto))
		sRturnResponse.put("beneficiary", new JSONObject(gson.toJson(ruralDto)));
		else
			sRturnResponse.put("beneficiary",  "");
		
		sRturnResponse.put("guid", guid);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		sRturnResponse.put("rural_urban",  rural_urban);
		
		
		return sRturnResponse.toString();
	}
	
	
	public static String urbanDtoResponse(UrbanDto urbanDto,String guid,boolean status,String errorCode, String errDes ,String rural_urban) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(urbanDto))
			sRturnResponse.put("beneficiary", new JSONObject(gson.toJson(urbanDto)));
		else
			sRturnResponse.put("beneficiary",  "");
		sRturnResponse.put("guid", guid);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		sRturnResponse.put("rural_urban",  rural_urban);
		
		return sRturnResponse.toString();
	}
	
	
	
}
