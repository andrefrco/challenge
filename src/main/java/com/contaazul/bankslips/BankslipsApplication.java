package com.contaazul.bankslips;

import com.contaazul.bankslips.config.EnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankslipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankslipsApplication.class, args);
	}

	@Bean
	public EnvironmentConfig buildEnvironmentConfig() {
		return ConfigFactory.create( EnvironmentConfig.class, System.getenv() );
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
