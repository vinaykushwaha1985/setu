package com.gov.nha.bis.server.kafka.producer.config;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.gov.nha.bis.server.dto.KycDataDto;

@EnableKafka
@Configuration
public class NhaKafkaKycProducerConfig {
	
	@Value("${kafka.bootstrap.servers}")
	 private String BOOTSTRAP_SERVERS_CONFIG;
	 
	 @Value("${kyc.kafka.group.in.config}")
	 private String GROUP_ID_CONFIG;

	  @Bean
	  public ProducerFactory<String, KycDataDto> kycFactory() {
		    Map<String, Object> props = new HashMap<>();
		    props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
		    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
		    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

	    return new DefaultKafkaProducerFactory<>(props);
	  }

	  @Bean
	  public KafkaTemplate<String, KycDataDto> kafkaKycTemplate() {
	    return new KafkaTemplate<>(kycFactory());
	  }


}
