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
import com.gov.nha.bis.server.dto.DemoDto;
import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.dto.KycPhotoDto;
import com.gov.nha.bis.server.dto.RationDto;

public class KycUpdateResponse {
	
public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	
	public static String getKycUpdateResponse(String refernceid,String status) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("refernceid", refernceid);
		
		
		
		return sRturnResponse.toString();
	}
	
	public static String getCardStatusUpdateResponse(String refernceid,String status) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("guid", refernceid);
		
		
		
		return sRturnResponse.toString();
	}
	
	
	public static String demoAuthDataListResponse(List<DemoDto> demoDtoList,long referenceId,boolean status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray demoArry = new JSONArray();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(demoDtoList))
		sRturnResponse.put("demoAuthData",  new JSONArray(gson.toJson(demoDtoList)));
		else
			sRturnResponse.put("demoAuthData",  demoArry);
		
		//sRturnResponse.put("referenceid", referenceId);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	
	public static String rationCardListResponse(List<RationDto> rationList,long referenceId,boolean status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		JSONArray demoArry = new JSONArray();
		
		Gson gson = new Gson();
	
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

	/**
	 * @param kycPhotoDto
	 * @param guid
	 * @param status
	 * @param errorCode
	 * @param errDes
	 * @param rural_urban
	 * @return
	 */
	public static String kycPhotoDtoResponse(KycPhotoDto kycPhotoDto,
			String guid,boolean status,String errorCode, String errDes,String rural_urban ) {
		// TODO Auto-generated method stub
		
		JSONObject sRturnResponse = new JSONObject();
		
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(kycPhotoDto))
		sRturnResponse.put("KYCPHOTO", new JSONObject(gson.toJson(kycPhotoDto)));
		else
			sRturnResponse.put("KYCPHOTO",  "");
		
		sRturnResponse.put("guid", guid);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		sRturnResponse.put("rural_urban",  rural_urban);
		
		
		return sRturnResponse.toString();
	}

	
}
