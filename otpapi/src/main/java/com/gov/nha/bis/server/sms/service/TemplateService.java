package com.gov.nha.bis.server.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.properties.ApplicationConstantConfig;

@Service
public class TemplateService {

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	
	public String getTemplateSms(String templateId) {
		
		switch (templateId) {
	// otp for add new member
		case "1007163593301170578":
			return applicationConstantConfig.BIS_TEMPLATE_OTP_1007163593301170578;
	// otp for download card		
		case "1007163593280345232":
			return applicationConstantConfig.BIS_TEMPLATE_OTP_1007163593280345232;
	// otp for access bis login	
		case "1007163593275058061":
			return applicationConstantConfig.BIS_TEMPLATE_OTP_1007163593275058061;
		// Kyc complete sms 	
		case "1007163593307833597":
			return applicationConstantConfig.BIS_TEMPLATE_SMS_1007163593307833597;
		// SMS for add new member
		case "1007163593317209694":
			return applicationConstantConfig.BIS_TEMPLATE_SMS_1007163593317209694;
			
		default:
			break;
		}
		return null;
	
	}
	
	
}
