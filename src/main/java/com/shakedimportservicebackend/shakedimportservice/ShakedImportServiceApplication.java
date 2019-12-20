package com.shakedimportservicebackend.shakedimportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EnableJpaRepositories("com.shakedimportservicebackend.shakedimportservice")
@EntityScan("com.shakedimportservicebackend.shakedimportservice.persistence.model")
public class ShakedImportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakedImportServiceApplication.class, args);
	}



}
