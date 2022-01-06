package com.gov.nha.bis.server.response;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class StateServiceResponse {
	
	public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	
	public static String stateServiceResponse(int stateCode,boolean status,
			String errorCode, String errDes ,String rural_urban) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("stateCode", stateCode);
		sRturnResponse.put("rural_urban", rural_urban);
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("errCode",  errorCode);
		sRturnResponse.put("errDes",  errDes);
		
		
		return sRturnResponse.toString();
	}
	
	
		

}
