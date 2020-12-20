package com.omblanco.springboot.webflux.api.mongo.app.web.controllers;

import static com.omblanco.springboot.webflux.api.commons.web.utils.BaseApiConstants.USER_BASE_URL_V1;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.omblanco.springboot.webflux.api.commons.web.controllers.user.CommonUserController;
import com.omblanco.springboot.webflux.api.mongo.app.web.dtos.UserDTO;
import com.omblanco.springboot.webflux.api.service.user.UserBO;
import com.omblanco.springboot.webflux.api.service.user.UserService;

import lombok.Builder;

/**
 * Controlador para los usuarios
 * Puede contener métodos para la carga de vistas html
 * 
 * @author oscar.martinezblanco
 *
 */
@Controller
@RequestMapping(USER_BASE_URL_V1)
public class UserController extends CommonUserController<UserDTO, String> {
    
    @Builder
    public UserController(UserService<String> service, ModelMapper modelMapper) {
        super(service, modelMapper);
    }
    
    @Override
    protected String getBaseUrl() {
        return USER_BASE_URL_V1;
    }
    
    @Override
    protected void updateBoToSave(UserDTO requestDto, UserBO<String> bo) {
        bo.setBirthdate(requestDto.getBirthdate());
        bo.setEmail(requestDto.getEmail());
        bo.setName(requestDto.getName());
        bo.setSurname(requestDto.getSurname());
    }
}
