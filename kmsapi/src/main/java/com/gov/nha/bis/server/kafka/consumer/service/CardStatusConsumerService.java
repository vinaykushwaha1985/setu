 /**
     * 
     *
     * @author VinayKushwaha
  */

package com.gov.nha.bis.server.kafka.consumer.service;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.CardStatusParam;
import com.gov.nha.bis.server.kafka.producer.service.CardStatusProducerService;
import com.gov.nha.bis.server.properties.ApplicationConstantConfig;
import com.gov.nha.bis.server.rest.service.BenUpdateCardStatusService;

@Service
public class CardStatusConsumerService {
	
	Logger logger = LoggerFactory.getLogger(CardStatusProducerService.class);
	
	@Autowired
	public BenUpdateCardStatusService benUpdateCardStatusService;
	
	@Autowired
	public ApplicationConstantConfig applicationConstantConfig;
	
	
	
	@KafkaListener(topics  = "cardStatus_json", groupId = "CONSUMER-1-GROUP", containerFactory = "kafkaListenerContainerFactory")
    public void consumemessage(CardStatusParam cardStatusParam){
	
		logger.info("Consumed Message-->" +cardStatusParam.getGuid());	
		
		logger.info("BigInteger.valueOf(cardStatusParam.getGuid()) Message-->" +BigInteger.valueOf(cardStatusParam.getGuid()));
		
		logger.info("Consumed Message-->" +cardStatusParam.getGuid());
		
		String res=	benUpdateCardStatusService.benUpdateCardStatus(cardStatusParam,applicationConstantConfig.DB_BEN_CARD_STATUS_UPDATE_URL);
		
		logger.info("cardStatusParam.getGuid() res-->"+res);
	
    }
	
	
	
}
