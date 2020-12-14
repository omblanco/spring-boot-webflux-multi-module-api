package com.omblanco.springboot.webflux.api.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.omblanco.springboot.webflux.api.app.web.dto.UserDTO;
import com.omblanco.springboot.webflux.api.commons.services.CommonService;
import com.omblanco.springboot.webflux.api.commons.web.dto.UserFilterDTO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserDAO;

import reactor.core.publisher.Mono;

/**
 * Interfaz del servicio de usuario
 * @author oscar.martinezblanco
 *
 */
public interface UserService extends CommonService<UserDTO, UserDAO<Long>, Long>{

    /**
     * Recupera usuarios paginados y filtrados
     * @param filter Filtro de búsqueda
     * @param pageable Paginación
     * @return Mono de página de usuarios
     */
    Mono<Page<UserDTO>> findByFilter(UserFilterDTO filter, Pageable pageable);
    
    /**
     * Busca un usuario por email
     * @param email Email
     * @return Usuario
     */
    Mono<UserDTO> findByEmail(String email);
}
