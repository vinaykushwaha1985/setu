/**
     * 
     *
     * @author VinayKushwaha
     *
     *
  **/

package com.gov.nha.bis.server.kafka.producer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.CardStatusParam;

@Service
public class CardStatusProducerService {
  
	Logger log = LoggerFactory.getLogger(CardStatusProducerService.class);

    private String topic = "cardStatus_json";

    private KafkaTemplate<String, CardStatusParam> kafkaTemplate;

    public CardStatusProducerService(KafkaTemplate<String, CardStatusParam> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   
    public void send(CardStatusParam cardStatusParam) {
        log.info("send to topic={} CardStatusParam={}", topic, cardStatusParam);
        kafkaTemplate.send("cardStatus_json", cardStatusParam);
    }
}
