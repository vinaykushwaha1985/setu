package com.gov.nha.bis.server.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource({"classpath:rationcard.properties"})
public class RationCardConstantConfig {

}
