package com.gov.nha.bis.server.controller;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
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
import com.gov.nha.bis.server.redis.cache.manager.OtpCacheManager;
import com.gov.nha.bis.server.response.OtpServiceResponse;
import com.gov.nha.bis.server.util.TransactionManager;

@RestController
@RequestMapping("/bis")
@CrossOrigin
public class NhaBisOtpVerifyController {
	
private static final Logger logger = LoggerFactory.getLogger(NhaBisOtpSenderController.class);
	

@Autowired
public OtpCacheManager otpCacheManager;
	
	
	@PostMapping(value="/otp/verify/1.0", produces=MediaType.APPLICATION_JSON_VALUE)
	public String smsVerifyRequest(@RequestBody SmsParam smsParam) {
		
		try {
			if(!ObjectUtils.isEmpty(smsParam)) {
				SmsOtpDto smsDto= new SmsOtpDto();
				
				
				smsDto=otpCacheManager.getStateDetails(smsParam.getMobile());
				
				logger.info("smsDto====="+smsDto.getOtp());
				logger.info("smsParam====="+smsParam.getOtp());
				
				logger.info("smsParam====="+smsParam.getMobile());
				
				if(smsDto.getOtp().equalsIgnoreCase(smsParam.getOtp())) {
					otpCacheManager.deleteOtp(smsParam.getMobile(), smsDto);
					return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "true", "200", "success")	;	
					
				}else {
					return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "201", "Incorrect OTP")	;
				}
			
			}else {
				return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "201", "Incorrect OTP")	;
			}
			
			
			
		}catch (Exception e) {
			logger.info(e.getMessage());// TODO: handle exception
			return OtpServiceResponse.otpServiceResponse(TransactionManager.getTransactionId(), "false", "201", "Incorrect OTP")	;
		}
		
		
		
		
			
		
	}

}
