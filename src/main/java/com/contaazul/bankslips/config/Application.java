package com.contaazul.bankslips.config;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.contaazul.bankslips")
@EnableJpaRepositories(basePackages = "com.contaazul.bankslips.repository")
@EntityScan(basePackages = "com.contaazul.bankslips.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public StatusConfiguration buildEnvironmentConfig() {
		return ConfigFactory.create( StatusConfiguration.class, System.getenv() );
	}

}
