package com.gov.nha.bis.server.rest.service;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.util.CheckRedisEnbleFunction;

@Service
public class SaveKycDataService {

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	
	private static final Logger logger = LogManager.getLogger(SaveKycDataService.class);
	
	public String saveKyc(String refernceid,String name,String relationshiptype,String relationshipname,String gender,
			String dob,String address,String statename,String districtname,String ekyctype,String claimedby,String usertype,
			String uidtoken,String schemeid,String ruralUrban, String pht,String stateCode,
			String districtCode,String blockCode,String villageCode,String townCode,String wardCode,String latestphoto,int mode) {
		
		
		String response="";
		
		if(CheckRedisEnbleFunction.convertArrayToList(applicationConstantConfig.SEARCH_SERVICE_REDIS_ENABLE_LIST.split(",")).contains(stateCode)) {
			
			response=getSaveKyc(getSaveKycRequestJson(refernceid, name, relationshiptype, relationshipname, gender, 
					dob, address, statename, districtname, ekyctype, claimedby, usertype, uidtoken, schemeid, ruralUrban, pht, stateCode,
					districtCode, blockCode, villageCode, townCode, wardCode,latestphoto,mode), applicationConstantConfig.SAVE_KYC_URL);
		}else {
			
			response=getSaveKyc(getSaveKycRequestJson(refernceid, name, relationshiptype, relationshipname, gender, 
				dob, address, statename, districtname, ekyctype, claimedby, usertype, uidtoken, schemeid, ruralUrban, pht, stateCode,
				districtCode, blockCode, villageCode, townCode, wardCode,latestphoto,mode), applicationConstantConfig.SAVE_KYC_URL);
		}
		return response;
	}
	
	
	public static String getSaveKycRequestJson(String refernceid,String name,String relationshiptype,
			String relationshipname,String gender,
			String dob,String address,String statename,String districtname,String ekyctype,String claimedby,String usertype,
			String uidtoken,String schemeid,String ruralUrban, String pht,String stateCode,String districtCode,
			String blockCode,String villageCode,String townCode,String wardCode,String latestphoto,int mode){

		JSONObject data= new JSONObject();
		data.put("refernceid", refernceid);
		data.put("name", name);
		data.put("gender", gender);
		
		data.put("relationshiptype", relationshiptype);
		data.put("relationshipname", relationshipname);
		
		data.put("dob_d", (ObjectUtils.isEmpty(dob)?"":dob.substring(8)));
		data.put("dob_m", (ObjectUtils.isEmpty(dob)?"":dob.substring(5, 7)));
		data.put("dob_y", (ObjectUtils.isEmpty(dob)?"":dob.substring(0, 4)));
		data.put("address", address); 
		data.put("statename", statename);
		data.put("districtname", districtname);
		data.put("pncode", "");
		data.put("claimedby", claimedby);
		data.put("usertype", usertype);
		data.put("uidtoken", uidtoken);
		data.put("schemeid", schemeid);
		
		data.put("ruralUrban", ruralUrban);
		data.put("pht", pht);
		
		data.put("stateCode", stateCode);
		data.put("districtCode", districtCode);
		data.put("blockCode", blockCode);
		data.put("villageCode", villageCode);
		data.put("townCode", townCode);
		data.put("wardCode", wardCode);
		data.put("ekyctype", ekyctype);
		data.put("latestphoto", latestphoto);		
		data.put("mode", mode);
		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getSaveKyc(String requestJson,String url){
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
