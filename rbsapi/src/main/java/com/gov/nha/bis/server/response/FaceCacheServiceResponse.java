package com.gov.nha.bis.server.response;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gov.nha.bis.server.dto.FaceDto;

public class FaceCacheServiceResponse {
	
	public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	
	public static String faceServiceResponse(String txn,boolean status,String errorCode, String errDes ) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("txn", txn);
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	public static String faceDtoResponse(FaceDto faceDto,String faceid,boolean status,String errorCode, String errDes
			) {
		JSONObject sRturnResponse = new JSONObject();
		
		Gson gson = new Gson();
	
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		
		if(!ObjectUtils.isEmpty(faceDto))
		sRturnResponse.put("faceData", new JSONObject(gson.toJson(faceDto)));
		else
			sRturnResponse.put("faceData",  new JSONObject());
		
		sRturnResponse.put("faceid", faceid);
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	
		

}
