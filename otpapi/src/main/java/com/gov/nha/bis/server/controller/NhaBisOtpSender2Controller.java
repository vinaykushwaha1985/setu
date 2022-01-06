package com.gov.nha.bis.server.controller;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gov.nha.bis.server.dto.SmsOtpDto;
import com.gov.nha.bis.server.dto.SmsParam;
import com.gov.nha.bis.server.model.SmsOtpEntity;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.redis.cache.manager.OtpCacheManager;
import com.gov.nha.bis.server.response.OtpServiceResponse;
import com.gov.nha.bis.server.service.SmsOtpService;
import com.gov.nha.bis.server.sms.service.SmsOtpApiService2;
import com.gov.nha.bis.server.sms.service.TemplateService;
import com.gov.nha.bis.server.util.GenerateOtpFunction;
import com.gov.nha.bis.server.util.StrListFunction;
import com.gov.nha.bis.server.util.TransactionManager;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaBisOtpSender2Controller {

	private static final Logger logger = LoggerFactory.getLogger(NhaBisOtpSender2Controller.class);

	@Autowired
	public OtpCacheManager otpCacheManager;

	@Autowired
	public SmsOtpApiService2 smsOtpApiService;

	@Autowired
	public SmsOtpService smsOtpService;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public TemplateService templateService;

	@PostMapping(value="/otp/sender/2.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String smsSenderRequest(@RequestBody SmsParam smsParam) {
		String otp="";
		try {
			String txn=TransactionManager.getTransactionId();
			logger.info(txn);
			logger.info("mobile :"+smsParam.getMobile());

			if(!ObjectUtils.isEmpty(smsParam)) {

				if(StrListFunction.convertArrayToList(applicationConstantConfig.BIS_SMS_GATEWAY_OTP_LIST.split(",")).contains(smsParam.getTemplateid())) {


					SmsOtpDto smsDto= new SmsOtpDto();


					otp =GenerateOtpFunction.generateOTP();



					smsDto.setMobile(smsParam.getMobile());
					smsDto.setOtp(otp);

					if(smsParam.getTemplateid().equalsIgnoreCase("1007163593301170578"))
						otpCacheManager.cacheStateDetails("A_"+smsParam.getMobile(), smsDto);
					else if(smsParam.getTemplateid().equalsIgnoreCase("1007163593280345232"))
						otpCacheManager.cacheStateDetails("D_"+smsParam.getMobile(), smsDto);
					else if(smsParam.getTemplateid().equalsIgnoreCase("1007163593275058061"))
						otpCacheManager.cacheStateDetails("L_"+smsParam.getMobile(), smsDto);
					//	String sms="Dear User, Your OTP to access NHA BIS portal is "+otp+". It will be valid for 3 minutes.NHA";
					
					String sms=templateService.getTemplateSms(smsParam.getTemplateid()).replace("{#var#}", otp);

					String sendRes=	smsOtpApiService.sendSms(sms, smsParam.getMobile(),smsParam.getTemplateid());

					if(!ObjectUtils.isEmpty(sendRes)) {
						JSONObject resJson=new JSONObject(sendRes);
						if(!ObjectUtils.isEmpty(resJson)) {

							if(resJson.getString("STATUS").equalsIgnoreCase("OK")) {

								JSONObject response=resJson.getJSONObject("RESPONSE");
								if(!ObjectUtils.isEmpty(response)) {
									if(response.getString("INFO").equalsIgnoreCase("SUBMITTED")) {
										SmsOtpEntity smsOtp= new SmsOtpEntity();
										smsOtp.setMobile(smsParam.getMobile());
										smsOtp.setTxn(txn);
										smsOtp.setOtp(Integer.valueOf(otp));
										smsOtp.setIp(smsParam.getIp());
										smsOtp.setSmsdate(new Date());
										smsOtp.setStatus(true);
										smsOtp.setRes(sendRes);
										smsOtpService.saveSmsOtpEntity(smsOtp);

										return OtpServiceResponse.otpServiceResponse(txn, "true", "", "")	;
									}
								}
							}

						}

					}


				}else {
					return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "", "")	;	
				}
			}else {


				return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "", "")	;

			}

		}catch (Exception e) {

			logger.info(e.getMessage());// TODO: handle exception
			return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "", "")	;
		}


		return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "", "")	;
	}



}
