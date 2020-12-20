package com.omblanco.springboot.webflux.api.mongo.app.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para el escaneo de repositorios
 * @author oscar.martinezblanco
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.omblanco.springboot.webflux.api.model"
})
public class RepositoriesConfig {

}
