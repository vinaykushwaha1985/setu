package com.gov.nha.bis.server.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.RationCardDto;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.RationCardDataService;

@Service
public class RationCardConsumerService {



	Logger logger = LoggerFactory.getLogger(RationCardConsumerService.class);

	@Autowired
	public RationCardDataService rationCardDataService;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@KafkaListener(topics  = "rationCard_json", groupId = "CONSUMER-4-GROUP", containerFactory = "kafkaRationListenerContainerFactory")
	public void consumemessage(RationCardDto dataDto){

		logger.info("RationCardDto Save Consumed Message-->" +dataDto.getGuid());	


		rationCardDataService.saveRationData(dataDto);

		logger.info("dataDto.getGuid() Message-->"+dataDto.getGuid()+": Save Status:" );

	}





}
