package com.gov.nha.bis.server.kafka.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.FamilySeccParam;

@Service
public class SeccProducerService {


	  
	Logger log = LoggerFactory.getLogger(SeccProducerService.class);

    private String topic = "secc_json";

    private KafkaTemplate<String, FamilySeccParam> kafkaTemplate;

    public SeccProducerService(KafkaTemplate<String, FamilySeccParam> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   
    public void send(FamilySeccParam familySeccParam) {
        log.info("send to topic={} secc_json={}", topic, familySeccParam);
        kafkaTemplate.send("secc_json", familySeccParam);
    }







}
