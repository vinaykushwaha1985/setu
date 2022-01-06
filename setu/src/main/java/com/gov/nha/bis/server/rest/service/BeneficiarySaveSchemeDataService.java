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
public class BeneficiarySaveSchemeDataService {

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(BeneficiarySaveSchemeDataService.class);
	
	public String benSchemeSave(String state_code,String district_code,String schemename,String schemeid,String rationcardno,
			String hhid,String guid,String memberid,String name,String fathername,String mothername,
			String spousename,String yob,String gender,
			String relation,String datasource,String apisource,String createdby,String urban_rural) {
		
		String response="";
		
		response=getSaveDemo(getbeneficiaryRequestJson( state_code, district_code, schemename, schemeid, rationcardno, hhid, guid, memberid, name, fathername, mothername, spousename, yob, gender,
				 relation, datasource, apisource, createdby, urban_rural), applicationConstantConfig.BEN_SCHEME_SAVE_DATA_URL);
		
		return response;
	}
	
	
	public  String getbeneficiaryRequestJson(String state_code,String district_code,String schemename,
			String schemeid,String rationcardno,String hhid,String guid,String memberid,String name,
			String fathername,String mothername,String spousename,String yob,String gender,
			String relation,String datasource,String apisource,String createdby,String urban_rural){

		JSONObject data= new JSONObject();
		
		data.put("state_code", state_code);
		data.put("district_code", district_code);
		data.put("schemename", schemename);
		data.put("schemeid", schemeid);
		data.put("rationcardno", rationcardno);
		data.put("hhid", hhid);
		data.put("guid", guid);
		data.put("memberid", memberid);
		data.put("name", name);
		data.put("fathername", fathername);
		data.put("mothername", mothername);
		data.put("spousename", spousename);
		data.put("yob", yob);
		data.put("gender", gender);
		data.put("relation", relation);
		data.put("datasource", datasource);
		data.put("createdby", createdby);
		data.put("urban_rural", urban_rural);
		logger.info(data.toString());
		return data.toString();
	}
	
	
		
	public  String getSaveDemo(String requestJson,String url){
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
	
	


	public String saveSchemeCard(String state_code,String district_code,String schemeid,String beneficicaryid,String guid,
			String createdby,String urban_rural) {
		
		String response="";
		
		response=getSaveDemo(getSchemeCardRequestJson( state_code, district_code, schemeid, beneficicaryid,guid, 
				createdby, urban_rural), applicationConstantConfig.BEN_SCHEME_CARD_SAVE_DATA_URL);
		
		return response;
	}

	public  String getSchemeCardRequestJson(String state_code,String district_code,
			String schemeid,String beneficicaryid,String guid,String createdby,String urban_rural){

		JSONObject data= new JSONObject();
		
		data.put("state_code", state_code);
		data.put("district_code", district_code);
		data.put("schemeid", schemeid);
		data.put("beneficiaryid", beneficicaryid);
		data.put("guid", guid);
		data.put("createby", createdby);
		data.put("rural_urban", urban_rural);
		logger.info(data.toString());
		return data.toString();
	}
	
	public String saveSchemeCardUpdate(String state_code,String district_code,String schemeid,String beneficicaryid,String guid,
			String createdby,String urban_rural,String memberrefid,String uidtoken,String uidenc,
			int ekyctype,int createcardstatus,int deliverycardstatus,int printcardstatus,String hhid,
			String mobile,String pmjayid,String name) {
		
		String response="";
		
		response=getSaveDemo(getSchemeCardUpdateRequestJson( state_code, district_code, schemeid, beneficicaryid,guid, 
				createdby, urban_rural,memberrefid,uidtoken,uidenc,
				ekyctype,createcardstatus,deliverycardstatus,printcardstatus,hhid,
				mobile,pmjayid,name), applicationConstantConfig.BEN_SCHEME_CARD_UPDATE_DATA_URL);
		
		return response;
	}

	public  String getSchemeCardUpdateRequestJson(String state_code,String district_code,
			String schemeid,String beneficicaryid,String guid,String createdby,String urban_rural,String memberrefid,
			String uidtoken,String uidenc,int ekyctype,int createcardstatus,int deliverycardstatus,int printcardstatus,
			String hhid,String mobile,String pmjayid,String name){

		JSONObject data= new JSONObject();
		
		data.put("statecode", state_code);
		data.put("districtcode", district_code);
		data.put("schemeid", schemeid);
		data.put("nfsaid", beneficicaryid);
		data.put("guid", guid);
		data.put("createdby", createdby);
		data.put("rural_urban", urban_rural);
		data.put("memberrefid", memberrefid);
		data.put("uidtoken", uidtoken);
		data.put("uidenc", uidenc);
		data.put("ekyctype", ekyctype);
		data.put("createcardstatus", createcardstatus);
		data.put("deliverycardstatus", deliverycardstatus);
		data.put("printcardstatus", printcardstatus);
		data.put("hhid", hhid);
		data.put("mobile", mobile);
		data.put("pmjayid", pmjayid);
		data.put("name", name);
		logger.info(data.toString());
		return data.toString();
	}

}
