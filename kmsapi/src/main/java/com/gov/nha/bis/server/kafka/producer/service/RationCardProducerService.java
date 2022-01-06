package com.gov.nha.bis.server.kafka.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.RationCardDto;

@Service
public class RationCardProducerService {

	  
	Logger log = LoggerFactory.getLogger(RationCardProducerService.class);

    private String topic = "demoData_json";

    private KafkaTemplate<String, RationCardDto> kafkaTemplate;

    public RationCardProducerService(KafkaTemplate<String, RationCardDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   
    public void send(RationCardDto rationCardDto) {
        log.info("send to topic={} rationCard_json={}", topic, rationCardDto);
        kafkaTemplate.send("rationCard_json", rationCardDto);
    }






}
