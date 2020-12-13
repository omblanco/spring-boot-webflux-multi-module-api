package com.omblanco.springboot.webflux.api.mongo.app.model.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.omblanco.springboot.webflux.api.mongo.app.model.entity.User;

import reactor.core.publisher.Mono;

public interface ReactiveMongoUserRepository extends ReactiveMongoRepository<User, String>, CustomUserRepository {
    
    /**
     * Busca un usuario por email
     * @param email email
     * @return Usuario
     */
    Mono<User> findByEmail(String email);
}
