package com.credit.card.application.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.credit.card.application"})
@EnableJpaRepositories(basePackages = {"com.credit.card.application"})
@EntityScan(basePackages = {"com.credit.card.application"})
public class ApplicationServer {
	
	public static void main(String[] args) {		
		SpringApplication.run(ApplicationServer.class, args);
		
	}
}
