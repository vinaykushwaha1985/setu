package com.gov.nha.bis.server.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.DemoDataDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.SaveDemoDataService;

@Service
public class DemoDataConsumerService {

	
	Logger logger = LoggerFactory.getLogger(DemoDataConsumerService.class);
	
	@Autowired
	public SaveDemoDataService saveDemoDataService;
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	
	@KafkaListener(topics  = "demoData_json", groupId = "CONSUMER-3-GROUP", containerFactory = "kafkaDemoListenerContainerFactory")
    public void consumemessage(DemoDataDto demoDataDto){
	
		logger.info("demoDataDto Save Consumed Message-->" +demoDataDto.getRefernceid());	
		
		
		saveDemoDataService.saveDemo(demoDataDto, applicationConstantConfig.SAVE_DEMO_URL);
		
		logger.info("demoDataDto.getRefernceid() Message-->"+demoDataDto.getRefernceid()+": Save Status:" );
	
    }
	


}
