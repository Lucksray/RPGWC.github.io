package com.qa.rpgwc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = "com.qa.rpgwc.Beans")
@ComponentScan(value = "com.qa.rpgwc.controllers")
@EntityScan(value = "com.qa.rpgwc.Entities")
@EnableJpaRepositories("com.qa.rpgwc.repo")
public class RpgwcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpgwcApplication.class, args);
		
	}

}
