package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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
import com.gov.nha.bis.server.sms.service.SmsOtpApiService;
import com.gov.nha.bis.server.sms.service.TcsSmsOtpApiService;
import com.gov.nha.bis.server.util.GenerateOtpFunction;
import com.gov.nha.bis.server.util.TransactionManager;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaBisOtpSenderController {

	private static final Logger logger = LoggerFactory.getLogger(NhaBisOtpSenderController.class);

	@Autowired
	public OtpCacheManager otpCacheManager;

	@Autowired
	public SmsOtpApiService smsOtpApiService;

	@Autowired
	public SmsOtpService smsOtpService;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;

	@Autowired
	public TcsSmsOtpApiService tcsSmsOtpApiService;

	@PostMapping(value="/otp/sender/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String smsSenderRequest(@RequestBody SmsParam smsParam) {
		String otp="";
		try {
			String txn=TransactionManager.getTransactionId();
			logger.info(txn);
			logger.info("mobile :"+smsParam.getMobile());
			
			if(!ObjectUtils.isEmpty(smsParam)) {
				SmsOtpDto smsDto= new SmsOtpDto();


				otp =GenerateOtpFunction.generateOTP();



				smsDto.setMobile(smsParam.getMobile());
				smsDto.setOtp(otp);

				otpCacheManager.cacheStateDetails(smsParam.getMobile(), smsDto);

				if(applicationConstantConfig.USE_SMS_SERVICE.equalsIgnoreCase("Y")) {
				//	String sms="Dear User, Your OTP to access NHA BIS portal is "+otp+". It will be valid for 3 minutes.NHA";
					String sms="Dear%20User,%20Your%20OTP%20to%20access%20NHA%20BIS%20portal%20is%20"+otp+".%20It%20will%20be%20valid%20for%203%20minutes.%0A%0ANHA";

					String sendRes=	smsOtpApiService.sendSms(sms, smsParam.getMobile());

					if(sendRes.contains("API000")) {

						SmsOtpEntity smsOtp= new SmsOtpEntity();
						smsOtp.setMobile(smsParam.getMobile());
						smsOtp.setTxn(txn);
						smsOtp.setOtp(Integer.valueOf(otp));
						smsOtp.setIp(smsParam.getIp());
						smsOtp.setSmsdate(new Date());
						smsOtp.setStatus(true);
						smsOtpService.saveSmsOtpEntity(smsOtp);

						return OtpServiceResponse.otpServiceResponse(txn, "true", "", "")	;
					}else {
						return OtpServiceResponse.otpServiceResponse(txn, "false", "", "")	;	

					}
				}else {

					String	txn1 =TransactionManager.otpTxnId(6);
					String sendRes2=	tcsSmsOtpApiService.sendSms(smsParam.getMobile(),otp,txn1);

					logger.info("sendRes2=="+sendRes2);
					if(!ObjectUtils.isEmpty(sendRes2)){
						JSONObject smsRes= new JSONObject(sendRes2);
						if(smsRes.getString("status").equalsIgnoreCase("Y")) {

							SmsOtpEntity smsOtp= new SmsOtpEntity();
							smsOtp.setMobile(smsParam.getMobile());
							smsOtp.setTxn(txn+"_"+txn1);
							smsOtp.setOtp(Integer.valueOf(otp));
							smsOtp.setIp(smsParam.getIp());
							smsOtp.setSmsdate(new Date());
							smsOtp.setStatus(true);
							smsOtpService.saveSmsOtpEntity(smsOtp);

							return OtpServiceResponse.otpServiceResponse(txn, "true", "", "")	;
						}else {
							return OtpServiceResponse.otpServiceResponse(txn, "false", "", "")	;	

						}
					}else {
						return OtpServiceResponse.otpServiceResponse(txn, "false", "", "")	;	

					}

				}

			}else {
				

				return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "", "")	;
			
			}

		}catch (Exception e) {
			
			logger.info(e.getMessage());// TODO: handle exception
			return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "", "")	;
		}



	}

}
