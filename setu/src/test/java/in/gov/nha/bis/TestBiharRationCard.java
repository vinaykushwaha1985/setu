package in.gov.nha.bis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.gov.nha.bis.server.util.NhaSSSLUtil;

public class TestBiharRationCard {



	public static void main(String[] args) {

		TestBiharRationCard tjk= new TestBiharRationCard();

		NhaSSSLUtil.setDefaultSSL();
		
		String	 reString = tjk.sendReq( tjk.accessCode(), "https://apis-prd.pmjay.gov.in/api/PMJAY/pmjay_get_accesstoken");
		System.out.println("reString=="+reString);
		JSONObject req= new JSONObject(reString);

		System.out.println(req.getString("AccessToken"));

		String accessToken=req.getString("AccessToken");

		String response=tjk.sendReq2(tjk.reqJson(accessToken, tjk.getCode()), "https://apis-prd.pmjay.gov.in/PMJAY/BR/getPDSDetail");

		System.out.println(response);

	}



	public String getCode() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = sdf.format(cal.getTime());
		System.out.println("Current date in String Format: " + strDate);

		String dateStr=	strDate.replaceAll("/", "").replaceAll(" ", "").replaceAll(":", "");
		System.out.println("dateStr=="+dateStr);
		
	return "10000"+dateStr+"00002";
	}




	public String reqJson(String accessToken,String token) {
		JSONObject req= new JSONObject();

		req.put("ID_Type", "2");
		req.put("ID_Number", "011000000001");
		req.put("Token", token);
		req.put("state_code", "10");
		req.put("Beneficiary_Consent", "Y");

		req.put("access_token", accessToken);

		return req.toString();
	}

	
	public String accessCode() {
		JSONObject req= new JSONObject();
		req.put("State", "10");
		req.put("UserId", "NXMxcnZhVXpBdGY4T20vV3BuMWNPZz09");
		req.put("Password", "TThzNk1Xd2lGZFVIQlEvZHJUT1pKZz09");
		req.put("AuthString", "emk1TWNManVpNHNEZnVOWnZJd3RVeFpaVyt4YW5aYURJY3JGc3F3WHd6bz06MDAwMDAwMDAwMDAwMTU0OjYzNzU3Mjg5NzI0ODc5NTcwNA==");

		return req.toString();
	}

	public   String sendReq(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{

			System.out.println("requestJson"+requestJson);
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(sAccessToken);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(url, entity, String.class);
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return returnStr;
	}



	public   String sendReq2(String requestJson,String url){
		String returnStr=null;
		RestTemplate restTemplate = new RestTemplate();
		try{
			NhaSSSLUtil.setDefaultSSL();

			System.out.println("requestJson"+requestJson);
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(sAccessToken);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);

			returnStr = restTemplate.postForObject(url, entity, String.class);
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return returnStr;
	}





}
