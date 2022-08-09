package com.qa.rpgwc.Beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class CustomBeans {

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
}
