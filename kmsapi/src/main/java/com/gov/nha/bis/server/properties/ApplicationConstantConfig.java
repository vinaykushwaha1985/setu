package com.gov.nha.bis.server.properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource({"classpath:application.properties"})
public class ApplicationConstantConfig {
	
	
	@Value("${SAVE_KYC_URL}")
	public String SAVE_KYC_URL;
	
	@Value("${DB_BEN_CARD_STATUS_UPDATE_URL}")
	public String DB_BEN_CARD_STATUS_UPDATE_URL;
	
	
	@Value("${SAVE_DEMO_URL}")
	public String SAVE_DEMO_URL;
	
	@Value("${DB_BEN_RATION_DATA_SAVE_URL}")
	public String DB_BEN_RATION_DATA_SAVE_URL;
	
	@Value("${DB_BEN_SECC_DATA_SAVE_URL}")
	public String DB_BEN_SECC_DATA_SAVE_URL;
	
	
}