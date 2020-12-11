package com.omblanco.springboot.webflux.api.model.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interfaz del respositorio JPA de usuario
 * @author ombla
 *
 */
interface UserRepositoryJpa extends JpaRepository<User, Long> {
    
    /**
     * Busca un usuario por email
     * @param email email
     * @return Usuario
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Búsqueda filtrada, paginada y ordenada
     * @param specification Especificación de búsqueda
     * @param pageable Paginación
     * @return Página de usuarios
     */
    Page<User> findAll(Specification<User> specification, Pageable pageable);
}
