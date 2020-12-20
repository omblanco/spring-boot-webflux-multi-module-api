package com.omblanco.springboot.webflux.api.mongo.app.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para el escaneo de controladores y componentes web
 * @author oscar.martinezblanco
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.omblanco.springboot.webflux.api.commons.web"
})
public class ControllersConfig {

}
