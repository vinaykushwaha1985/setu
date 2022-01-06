package com.gov.nha.bis.server.rest.service;

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
public class SaveSubSchemeDataService {
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	
	private static final Logger logger = LogManager.getLogger(SaveSubSchemeDataService.class);
	
	public String saveSubScheme(String ruralUrban,String stateCode,String districtCode,
			String schemeid,String subSchemeid,String guid,String refrenceid,String documentid,
			String documentPhoto,String userid) {
		
		
		String response="";
		
		
			
			response=getSaveSubScheme(getSaveSubSchemeRequestJson(ruralUrban, stateCode, districtCode,
					 schemeid, subSchemeid, guid, refrenceid, documentid,
					 documentPhoto, userid), applicationConstantConfig.SAVE_SUB_SCHEME_DOCUMENT_URL);
		
		return response;
	}
	
	
	public static String getSaveSubSchemeRequestJson(String ruralUrban,String stateCode,String districtCode,
			String schemeid,String subSchemeid,String guid,String refrenceid,String documentid,
						String documentPhoto,String userid){

				
		
		
		JSONObject data= new JSONObject();
		data.put("rural_urban", ruralUrban);
		data.put("statecode", stateCode);
		data.put("districtcode", districtCode);
		
		data.put("schemeid", schemeid);
		data.put("subschemeid", subSchemeid);
		
		data.put("guid",guid);
		data.put("refernceid",refrenceid);
		data.put("documentid",documentid);
		data.put("documentphoto", documentPhoto); 
		data.put("createdby", userid);
		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public  String getSaveSubScheme(String requestJson,String url){
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
