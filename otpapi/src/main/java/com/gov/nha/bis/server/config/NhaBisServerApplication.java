package com.gov.nha.bis.server.config;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableCaching
@EntityScan( basePackages = {"com.gov.nha.*"})
@ComponentScan(basePackages = {"com.gov.nha.*"})
@EnableJpaRepositories("com.gov.nha.*")
@EnableTransactionManagement(proxyTargetClass=true)
@SpringBootApplication(scanBasePackages="com.gov.nha.*", exclude = { SecurityAutoConfiguration.class })
public class NhaBisServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhaBisServerApplication.class, args);
	}

}
