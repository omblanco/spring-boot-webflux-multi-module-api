package com.omblanco.springboot.webflux.api.app.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para el escaneo de servicios
 * @author oscar.martinezblanco
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.omblanco.springboot.webflux.api.service"
})
public class ServicesConfig {

}
