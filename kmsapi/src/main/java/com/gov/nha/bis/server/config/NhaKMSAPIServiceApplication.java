/**
     * 
     *
     * @author VinayKushwaha
     *
     *
  **/

package com.gov.nha.bis.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan(basePackages = {"com.gov.nha.*"})
@SpringBootApplication(scanBasePackages="com.gov.nha.*", exclude = { SecurityAutoConfiguration.class })
public class NhaKMSAPIServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhaKMSAPIServiceApplication.class, args);
	}

}
