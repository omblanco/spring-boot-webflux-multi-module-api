package com.omblanco.springboot.webflux.api.model.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.omblanco.springboot.webflux.api.model.entity.user.UserDAO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserFilterDAO;
import com.omblanco.springboot.webflux.api.model.repository.CommonRepository;

import reactor.core.publisher.Mono;

/**
 * Interfaz para el repositorio del UserDAO
 * @author ombla
 *
 * @param <ID> Tipo de dato del id del usuario
 */
public interface UserRepository<ID> extends CommonRepository<UserDAO<ID>, ID> {
    
    /**
     * Busca un usuario por email
     * @param email Email del usuario a buscar
     * @return Usuario
     */
    Mono<UserDAO<ID>> findByEmail(String email);
    
    /**
     * Búsqueda filtrada, paginada y ordenada
     * @param filter Filtro de usuarios
     * @param pageable Paginación y ordenación
     * @return Página de usuarios
     */
    Mono<Page<UserDAO<ID>>> findAll(UserFilterDAO filter, Pageable pageable);
}

