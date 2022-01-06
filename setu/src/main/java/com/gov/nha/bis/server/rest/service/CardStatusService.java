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
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class CardStatusService {


	@Autowired
	ApplicationConstantConfig applicationConstantConfig;

	private static final Logger logger = LogManager.getLogger(CardStatusService.class);

	public String getCardStatus(String state_code,String district_code,String family_mem_id,String uid_aadhaar,String rationCard,String rural_urban) {

		String response="";

		response=getData(getRequestJson(state_code,district_code,family_mem_id,uid_aadhaar,rationCard,rural_urban), 
				applicationConstantConfig.NFSA_CARD_STATUS_URL);

		return response;
	}


	public String getCardData(String state_code,String district_code,String uid_aadhaar,String rationCard,String rural_urban) {

		String response="";

		response=getData(getCardDataRequestJson(state_code,district_code,uid_aadhaar,rationCard,rural_urban), 
				applicationConstantConfig.NFSA_CARD_STATUS_URL);

		return response;
	}


	public static String getRequestJson(String state_code,String district_code,String family_mem_id,String uid_aadhaar,String rationCard,String rural_urban){

		JSONObject data= new JSONObject();
		data.put("nfsaid", rationCard);
		data.put("memberid", family_mem_id);
		data.put("uidenc", uid_aadhaar);

		data.put("statecode", state_code);
		data.put("districtcode", district_code);
		data.put("rural_urban", rural_urban);

		logger.info(data.toString());
		return data.toString();
	}



	public static String getCardDataRequestJson(String state_code,String district_code,String uid_aadhaar,String rationCard,String rural_urban){

		JSONObject data= new JSONObject();
		data.put("nfsaid", rationCard);

		data.put("uidenc", uid_aadhaar);

		data.put("statecode", state_code);
		data.put("districtcode", district_code);
		data.put("rural_urban", rural_urban);

		logger.info(data.toString());
		return data.toString();
	}



	public String getAyushmanCard(String nfsaid,String aadhaar, String statecode,String districtcode) {

		return getData(requestAyushman(nfsaid, aadhaar,  statecode, districtcode), 
				applicationConstantConfig.NFSA_AYUSHMAN_CARD_URL);
	}




	private String requestAyushman(String nfsaid, String aadhaar, String statecode, String districtcode) {
		JSONObject data= new JSONObject();

		data.put("nfsaid", nfsaid);
		data.put("aadhaar", aadhaar);
		data.put("statecode", statecode);
		data.put("districtcode", districtcode);

		logger.info(data.toString());
		return data.toString();
	}





	public  String getData(String requestJson,String url){
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
