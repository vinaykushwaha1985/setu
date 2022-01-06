package com.gov.nha.bis.server.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.BlockDto;
import com.gov.nha.bis.server.dto.DistrictDto;
import com.gov.nha.bis.server.dto.DistrictUrbanDto;
import com.gov.nha.bis.server.dto.RuralDto;
import com.gov.nha.bis.server.dto.StateDto;
import com.gov.nha.bis.server.dto.TownDto;
import com.gov.nha.bis.server.dto.VillageDto;
import com.gov.nha.bis.server.dto.WardDto;

public class VillageServiceResponse {
	
	public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	
	@SuppressWarnings("deprecation")
	public static String villageListResponse(List<RuralDto> ruralList,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray villageArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!StringUtils.isEmpty(ruralList))
		sRturnResponse.put("villageData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("villageData",  villageArry);
		
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	
	public static String stateListResponse(List<StateDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}


	public static String districtListResponse(List<DistrictDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}

	
	public static String districtUrbanListResponse(List<DistrictUrbanDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}


	
	public static String blockListResponse(List<BlockDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}


	
	public static String villageListResponse(List<VillageDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}

	public static String townListResponse(List<TownDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}

	
	public static String wardListResponse(List<WardDto> ruralList,String requestType,String status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray stateDataArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(ruralList))
			sRturnResponse.put("stateData",  new JSONArray(gson.toJson(ruralList)));
		else
			sRturnResponse.put("stateData",  stateDataArry);
	
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}


	
	
	

}
