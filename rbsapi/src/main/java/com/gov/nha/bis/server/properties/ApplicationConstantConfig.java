package com.gov.nha.bis.server.properties;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource({"classpath:application.properties"})
public class ApplicationConstantConfig {
	
	@Value("${kafka.ben.card.status.update.url}")
	public String KAFKA_BEN_CARD_STATUS_UPDATE_URL;
	
	@Value("${kafka.ben.kyc.data.save.url}")
	public String KAFKA_BEN_KYC_DATA_SAVE_URL;
	
	@Value("${kafka.ben.demo.data.save.url}")
	public String KAFKA_BEN_DEMO_DATA_SAVE_URL;
	
	@Value("${kafka.ben.ration.data.save.url}")
	public String KAFKA_BEN_RATION_DATA_SAVE_URL;
	
	
	@Value("${kafka.ben.secc.family.data.save.url}")
	public String KAFKA_BEN_SECC_FAMILY_DATA_SAVE_URL;
}