package com.gov.nha.bis.server.sms.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.util.NhaSSSLUtil;

@Service
public class SmsOtpApiService2 {


	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	private static final Logger logger = LoggerFactory.getLogger(SmsOtpApiService2.class);

	public  String sendSms(String massage,String mobile,String templateid) {
		String smsResponse="";
		StringBuilder sb = new StringBuilder();
		try {
				NhaSSSLUtil.setDefaultSSL();
			
			String requestUrl  = applicationConstantConfig.BIS_SMS_GATEWAY_URL  +
					"?user=" + applicationConstantConfig.BIS_SMS_GATEWAY_USER +
					"&authkey=" + applicationConstantConfig.BIS_SMS_GATEWAY_AUTH_KEY+
					"&sender=" + applicationConstantConfig.BIS_SMS_GATEWAY_SENDER_ID +
					"&mobile=" + mobile+
					"&text=" + massage+
					"&templateid=" + templateid +
					"&entityid=" + applicationConstantConfig.BIS_SMS_GATEWAY_ENTITY_ID;

			logger.info(requestUrl);
			URL url = new URL(requestUrl);
			HttpsURLConnection connect = (HttpsURLConnection)url.openConnection();

			smsResponse=connect.getResponseMessage();
			logger.info("connect.getResponseMessage()==="+connect.getResponseMessage());
			logger.info("connect.getContentEncoding()=="+connect.getContentEncoding());
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((connect.getInputStream())));

			

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			logger.info(sb.toString());
			
			connect.disconnect();

		} catch(Exception ex) {
			logger.info(ex.getMessage());

		}
		return sb.toString();
	}



}
