package com.omblanco.springboot.webflux.api.app.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase de configuración para los repositorios de JPA
 * @author oscar.martinezblanco
 *
 */
@Configuration
@EntityScan(basePackages = {"com.omblanco.springboot.webflux.api.model"})
@EnableJpaRepositories(basePackages = "com.omblanco.springboot.webflux.api.model")
public class JpaRepositoriesConfig {

}
