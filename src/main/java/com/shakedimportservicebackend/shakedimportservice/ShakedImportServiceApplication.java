package com.shakedimportservicebackend.shakedimportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.shakedimportservicebackend.shakedimportservice.*")
@EnableJpaRepositories("com.shakedimportservicebackend.shakedimportservice")
@EntityScan("com.shakedimportservicebackend.shakedimportservice.models")
public class ShakedImportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakedImportServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
