package com.omblanco.springboot.webflux.api.app.web.controllers;

import static com.omblanco.springboot.webflux.api.commons.web.utils.BaseApiConstants.USER_BASE_URL_V2;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omblanco.springboot.webflux.api.app.web.dto.UserDTO;
import com.omblanco.springboot.webflux.api.commons.web.controllers.user.CommonUserController;
import com.omblanco.springboot.webflux.api.service.user.UserBO;
import com.omblanco.springboot.webflux.api.service.user.UserService;

import lombok.Builder;

/**
 * RestController para los usuarios
 * En este caso la implementación es igual al UserController
 * Al usar la anotación @RestController este controlador sólo puede devolver objetos json
 * por no tanto no puede cargar vistas html a diferencia del UserController
 * No necesita de la anotación @ResponseBody al devolver los resultados
 * 
 * @author oscar.martinezblanco
 *
 */
@RestController
@RequestMapping(USER_BASE_URL_V2)
public class UserRestController extends CommonUserController<UserDTO, Long> {

    @Builder
    public UserRestController(UserService<Long> service, ModelMapper modelMapper) {
        super(service, modelMapper);
    }

    @Override
    protected String getBaseUrl() {
        return USER_BASE_URL_V2;
    }

    @Override
    protected void updateBoToSave(UserDTO requestDto, UserBO<Long> bo) {
        bo.setBirthdate(requestDto.getBirthdate());
        bo.setEmail(requestDto.getEmail());
        bo.setName(requestDto.getName());
        bo.setSurname(requestDto.getSurname());
    }
}
