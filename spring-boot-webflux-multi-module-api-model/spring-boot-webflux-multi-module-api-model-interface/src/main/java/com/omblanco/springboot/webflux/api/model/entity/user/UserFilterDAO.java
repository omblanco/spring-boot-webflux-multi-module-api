package com.omblanco.springboot.webflux.api.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Clase de filtro para los usuarios
 * @author ombla
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserFilterDAO {
    
    private String name;
    
    private String surname;
    
    private String email;
}
