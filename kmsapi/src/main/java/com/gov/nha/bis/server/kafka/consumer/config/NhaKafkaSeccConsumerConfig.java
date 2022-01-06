package com.gov.nha.bis.server.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.gov.nha.bis.server.dto.FamilySeccParam;

@EnableKafka
@Configuration
public class NhaKafkaSeccConsumerConfig {
	
	 @Value("${kafka.bootstrap.servers}")
	 private String BOOTSTRAP_SERVERS_CONFIG;
	 
	 @Value("${secc.kafka.group.in.config}")
	 private String GROUP_ID_CONFIG;
	 

@Bean
public ConsumerFactory<String, FamilySeccParam> seccConsumerFactory(){
    Map<String,Object> map = new HashMap<>();
    map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS_CONFIG);
    map.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
    map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
    map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), 
  		  new JsonDeserializer<>(FamilySeccParam.class));
}

@Bean
public ConcurrentKafkaListenerContainerFactory<String,FamilySeccParam> kafkaSeccListenerContainerFactory(){
    ConcurrentKafkaListenerContainerFactory<String,FamilySeccParam> kafkaSeccListenerContainerFactory= new ConcurrentKafkaListenerContainerFactory<>();
    kafkaSeccListenerContainerFactory.setConsumerFactory(seccConsumerFactory());
    return kafkaSeccListenerContainerFactory;
}











}
