package com.omblanco.springboot.webflux.api.mongo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de configuración de Spring Boot
 * @author oscar.martinezblanco
 *
 */

@ComponentScan(basePackages = {
        "com.omblanco.springboot.webflux.api.mongo.app",
        "com.omblanco.springboot.webflux.api.model"
})
@SpringBootApplication
public class SpringBootWebfluxSimpleApiMongoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxSimpleApiMongoAppApplication.class, args);
	}
}
