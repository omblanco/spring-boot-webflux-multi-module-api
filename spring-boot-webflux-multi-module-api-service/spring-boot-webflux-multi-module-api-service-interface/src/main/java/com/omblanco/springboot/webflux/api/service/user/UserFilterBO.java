package com.omblanco.springboot.webflux.api.service.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Filtro para los usuarios de la capa de negocio
 * @author oscar.martinezblanco
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserFilterBO {
    
    private String name;
    
    private String surname;
    
    private String email;
}
