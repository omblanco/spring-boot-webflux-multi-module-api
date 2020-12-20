package com.omblanco.springboot.webflux.api.mongo.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * Clase de configuración para habilitar los repositorios de mongo
 * @author oscar.martinezblanco
 *
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = {"com.omblanco.springboot.webflux.api.model"})
public class MongoRepositoriesConfig {

}
