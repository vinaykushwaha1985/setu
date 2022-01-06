package com.gov.nha.bis.server.rest.service;

import java.math.BigInteger;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.gov.nha.bis.server.dto.BeneficiaryParam;
import com.gov.nha.bis.server.dto.RationParam;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class SaveNfsaBeneficiaryService {

	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	private static final Logger logger = LogManager.getLogger(GenerateGuidPmjayIdService.class);
	
	
	


	public String saveNfsaBeneficiary(BeneficiaryParam beneficiaryParam) {
		
		String response="";
		Gson gson = new Gson();
		
		
		
		response=saveData(gson.toJson(beneficiaryParam), 
				applicationConstantConfig.SAVE_NFSA_BENEFICIARY_URL);
		
		return response;
	}
	
	
	
	public String saveAyshmanCard(String nfsaid, String memberid, BigInteger guid, String uidtoken, String uidenc,
			int statecode, String districtcode, String ekyctype, int status, String rural_urban, String mobile,
			String pmjayid,String name) {
		
		String response="";
		response=saveData(requestSave(nfsaid,memberid,guid, uidtoken,uidenc,
				 statecode, districtcode,  ekyctype,  status,  rural_urban,  mobile,
				 pmjayid,name), 
				applicationConstantConfig.SAVE_AYUSHMAN_CARD_URL);
		return response;
	}
	
	
	
	public String saveRationData(RationParam rPram) {

		String response="";
		Gson gson = new Gson();
		
			response=saveData(gson.toJson(rPram), applicationConstantConfig.NFSA_RATION_CARD_DATA_SAVE_URL);

			return response;
	}

	
	
	public String requestSave(String nfsaid, String memberid, BigInteger guid, String uidtoken, 
			String uidenc,
			int statecode, String districtcode, String ekyctype, int status, String rural_urban,
			String mobile,
			String pmjayid,String name) {
		
		JSONObject data= new JSONObject();
		data.put("nfsaid", nfsaid);
		data.put("memberid", memberid);
		data.put("guid", guid);
		
		data.put("uidtoken", uidtoken);
		data.put("uidenc", new String(Base64.encode(uidenc.getBytes())));
		
		data.put("statecode", statecode);
		data.put("districtcode",districtcode);
		data.put("ekyctype",ekyctype);
		data.put("status", status); 
		data.put("rural_urban", rural_urban);
		data.put("mobile", mobile);
		data.put("pmjayid", pmjayid);
		data.put("name", name);
		
		
		logger.info(data.toString());
		return data.toString();
	}
	
	
	public String saveKyc(String refernceid,String name,String relationshiptype,String relationshipname,String gender,
			String dob,String address,String statename,String districtname,String ekyctype,String claimedby,String usertype,
			String uidtoken,String schemeid,String ruralUrban, String pht,String stateCode,
			String districtCode,int mode) {
		
		
		String response="";
			
			response=saveData(getSaveKycRequestJson(refernceid, name, relationshiptype, relationshipname, gender, 
				dob, address, statename, districtname, ekyctype, claimedby, usertype, uidtoken, schemeid, ruralUrban, pht, stateCode,
				districtCode,mode), applicationConstantConfig.SAVE_NFSA_KYC_URL);
		
		return response;
	}
	
	
	public static String getSaveKycRequestJson(String refernceid,String name,String relationshiptype,
			String relationshipname,String gender,
			String dob,String address,String statename,String districtname,String ekyctype,String claimedby,String usertype,
			String uidtoken,String schemeid,String ruralUrban, String pht,String stateCode,String districtCode,
			int mode){

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
		data.put("ekyctype", ekyctype);
		data.put("mode", mode);
		
		
		logger.info(data.toString());
		return data.toString();
	}

		
	public  String saveData(String requestJson,String url){
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
