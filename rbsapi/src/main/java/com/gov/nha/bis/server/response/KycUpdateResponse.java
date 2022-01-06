package com.gov.nha.bis.server.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.DemoDataDto;
import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.dto.RationCardDto;

public class KycUpdateResponse {
	
public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	
	public static String getKycUpdateResponse(String refernceid,boolean status,String errCode, String errDes,
			String stateCode,String ruralUrban) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("refernceid", refernceid);
		
		sRturnResponse.put("errCode", errCode);
		sRturnResponse.put("errDes", errDes);
		sRturnResponse.put("rural_urban", ruralUrban);
		sRturnResponse.put("state_code", stateCode);
		
		return sRturnResponse.toString();
	}
	
	public static String getCardStatusUpdateResponse(String refernceid,String status) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("guid", refernceid);
		
		
		
		return sRturnResponse.toString();
	}
	
	public static String kycDataDtoResponse(KycDataDto kycDataDto,String guid,boolean status,String errorCode, String errDes,String rural_urban ) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(kycDataDto))
		sRturnResponse.put("KYCDATA", new JSONObject(gson.toJson(kycDataDto)));
		else
			sRturnResponse.put("KYCDATA",  "");
		
		sRturnResponse.put("guid", guid);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		sRturnResponse.put("rural_urban",  rural_urban);
		
		
		return sRturnResponse.toString();
	}
		

	public static String demoDataDtoResponse(DemoDataDto demoDataDto,String guid,boolean status,String errorCode, String errDes,String rural_urban ) {
		JSONObject sRturnResponse = new JSONObject();
		
		List<DemoDataDto>	demoDtoList =new ArrayList<DemoDataDto>();
		Gson gson = new Gson();
	
		if(!ObjectUtils.isEmpty(demoDataDto))
			demoDtoList.add(demoDataDto);
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(demoDataDto))
			sRturnResponse.put("demoAuthData",  new JSONArray(gson.toJson(demoDtoList)));
		else
			sRturnResponse.put("demoAuthData",  new JSONArray());
		
		sRturnResponse.put("guid", guid);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		sRturnResponse.put("rural_urban",  rural_urban);
		
		
		return sRturnResponse.toString();
	}

	public static String rationCardListResponse(RationCardDto rationCardDto,long referenceId,boolean status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray demoArry = new JSONArray();
		
		Gson gson = new Gson();
	
		List<RationCardDto> rationList = new ArrayList<RationCardDto>();
		
		if(!ObjectUtils.isEmpty(rationCardDto))
			rationList.add(rationCardDto);
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(rationList))
		sRturnResponse.put("rationCardData",  new JSONArray(gson.toJson(rationList)));
		else
			sRturnResponse.put("rationCardData",  demoArry);
		
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
}
