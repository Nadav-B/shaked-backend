package com.shaked.service;

import com.shaked.service.importer.ModulesImproter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.shaked.service.*")
@EnableJpaRepositories("com.shaked.service")
@EntityScan("com.shaked.service.models")
public class ShakedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShakedServiceApplication.class, args);
	}

	@Bean
	public ModulesImproter importJob (ModulesImproter modulesImproter){
		return modulesImproter;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
