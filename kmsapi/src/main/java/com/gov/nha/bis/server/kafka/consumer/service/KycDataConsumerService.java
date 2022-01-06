/**
 * 
 */
package com.gov.nha.bis.server.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.KycDataDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.SaveKycDataService;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Service
public class KycDataConsumerService {

	
	Logger logger = LoggerFactory.getLogger(KycDataConsumerService.class);
	
	@Autowired
	public SaveKycDataService saveKycDataService;
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	
	@KafkaListener(topics  = "kycData_json", groupId = "CONSUMER-2-GROUP", containerFactory = "kafkaKycListenerContainerFactory")
    public void consumemessage(KycDataDto kycDataDto){
	
		logger.info("Kyc Save Consumed Message-->" +kycDataDto.getRefernceid());	
		
		
		saveKycDataService.saveKyc(kycDataDto, applicationConstantConfig.SAVE_KYC_URL);
		
		logger.info("kycDataDto.getRefernceid() Message-->"+kycDataDto.getRefernceid()+": Save Status:" );
	
    }
	
}
