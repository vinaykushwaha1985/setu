package com.gov.nha.bis.server.response;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class CardStatusResponse {
	
public static SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:ss");
	
	
	
	public static String getCardStatusUpdateResponse(String refernceid,boolean status) {
		JSONObject sRturnResponse = new JSONObject();
		
		
		sRturnResponse.put("ts", mdyFormat.format(new Date()));
		sRturnResponse.put("status", status);
		sRturnResponse.put("guid", refernceid);
		
		
		
		return sRturnResponse.toString();
	}
	
	
	
}
