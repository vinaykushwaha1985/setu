package com.gov.nha.bis.server.kafka.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gov.nha.bis.server.dto.DemoDataDto;


@Service
public class DemoDataProducerService {
	  
		Logger log = LoggerFactory.getLogger(DemoDataProducerService.class);

	    private String topic = "demoData_json";

	    private KafkaTemplate<String, DemoDataDto> kafkaTemplate;

	    public DemoDataProducerService(KafkaTemplate<String, DemoDataDto> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

	   
	    public void send(DemoDataDto demoDataDto) {
	        log.info("send to topic={} demoData_json={}", topic, demoDataDto);
	        kafkaTemplate.send("demoData_json", demoDataDto);
	    }




}
