package com.omblanco.springboot.webflux.api.mongo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Clase principal de configuración de Spring Boot
 * @author oscar.martinezblanco
 *
 */
@EnableMongoRepositories(basePackages = "com.omblanco.springboot.webflux.api.model")
@ComponentScan("com.omblanco.springboot.webflux.api.mongo.app")
@SpringBootApplication
public class SpringBootWebfluxSimpleApiMongoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxSimpleApiMongoAppApplication.class, args);
	}
}
