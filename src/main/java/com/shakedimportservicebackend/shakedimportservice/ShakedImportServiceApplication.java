package com.shakedimportservicebackend.shakedimportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication


@EnableJpaRepositories("com.shakedimportservicebackend.shakedimportservice")
@EntityScan("com.shakedimportservicebackend.shakedimportservice.persistence.model")
public class ShakedImportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakedImportServiceApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/articles").allowedOrigins("http://localhost:8080");
			}
		};
	}

}
