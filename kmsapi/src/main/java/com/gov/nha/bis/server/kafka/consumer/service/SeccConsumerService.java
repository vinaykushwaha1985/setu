package com.gov.nha.bis.server.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.FamilySeccParam;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.SeccDataService;

@Service
public class SeccConsumerService {

	Logger logger = LoggerFactory.getLogger(SeccConsumerService.class);

	@Autowired
	public SeccDataService seccDataService;

	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;


	@KafkaListener(topics  = "secc_json", groupId = "CONSUMER-5-GROUP", containerFactory = "kafkaSeccListenerContainerFactory")
	public void consumemessage(FamilySeccParam dataDto){

		logger.info("RationCardDto Save Consumed Message-->" +dataDto.getGuid());	


		seccDataService.saveSeccData(dataDto);

		logger.info("dataDto.getGuid() Message-->"+dataDto.getGuid()+": Save Status:" );

	}







}
