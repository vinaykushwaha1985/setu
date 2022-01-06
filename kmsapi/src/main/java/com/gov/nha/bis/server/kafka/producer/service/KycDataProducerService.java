/**
 * 
 */
package com.gov.nha.bis.server.kafka.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.KycDataDto;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com
 */
@Service
public class KycDataProducerService {
	  
		Logger log = LoggerFactory.getLogger(KycDataProducerService.class);

	    private String topic = "kycData_json";

	    private KafkaTemplate<String, KycDataDto> kafkaTemplate;

	    public KycDataProducerService(KafkaTemplate<String, KycDataDto> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

	   
	    public void send(KycDataDto kycDataDto) {
	        log.info("send to topic={} kycData_json={}", topic, kycDataDto);
	        kafkaTemplate.send("kycData_json", kycDataDto);
	    }


}
