package com.omblanco.springboot.webflux.api.mongo.app.web.controllers;

import static com.omblanco.springboot.webflux.api.commons.utils.BaseApiConstants.USER_BASE_URL_V2;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omblanco.springboot.webflux.api.commons.web.controllers.CommonController;
import com.omblanco.springboot.webflux.api.commons.web.dto.UserFilterDTO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserDAO;
import com.omblanco.springboot.webflux.api.mongo.app.web.dtos.UserDTO;
import com.omblanco.springboot.webflux.api.service.user.UserBO;
import com.omblanco.springboot.webflux.api.service.user.UserFilterBO;
import com.omblanco.springboot.webflux.api.service.user.UserService;

import lombok.Builder;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

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
public class UserRestController extends CommonController<UserDTO, UserBO<String>, UserDAO<String>, UserService<String>, String> {

    private ModelMapper modelMapper;
    
    @Builder
    public UserRestController(UserService<String> service, ModelMapper modelMapper) {
        super(service);
        this.modelMapper = modelMapper;
    }

    /**
     * Método que recupera Usuarios paginados
     * @param filter Filtro de búsqueda
     * @param pageable Paginación y ordenación
     * @return Página de usuarios
     */
    @GetMapping
    public Mono<ResponseEntity<CorePublisher<?>>> findByFilter(UserFilterDTO filter,
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Long size, Long page) {
        
        if (size == null & page == null) {
            return super.findAll();
        }
        
        return Mono.just(ResponseEntity.ok().contentType(APPLICATION_JSON)
                .body(service.findByFilter(convertFilterToDao(filter), pageable).map(this::convertPageToBo)));
    }

    @Override
    protected String getBaseUrl() {
        return USER_BASE_URL_V2;
    }
    
    /**
     * Transforma un filtro dto en un filtro bo
     * @param dto Filtro de la capa de vista
     * @return Filtro de la capa de negocio
     */
    private UserFilterBO convertFilterToDao(UserFilterDTO dto) {
        return modelMapper.map(dto, UserFilterBO.class);
    }

    @Override
    protected void updateBoToSave(UserDTO requestDto, UserBO<String> bo) {
        bo.setBirthdate(requestDto.getBirthdate());
        bo.setEmail(requestDto.getEmail());
        bo.setName(requestDto.getName());
        bo.setSurname(requestDto.getSurname());
    }
    
    @Override
    protected UserDTO convertToDto(UserBO<String> bo) {
        return modelMapper.map(bo, UserDTO.class);
    }

    @Override
    protected Page<UserDTO> convertPageToBo(Page<UserBO<String>> boPage) {
        return new PageImpl<UserDTO>(boPage.getContent().stream().map(user -> {
            return this.convertToDto(user);
        }).collect(Collectors.toList()), boPage.getPageable(), boPage.getTotalElements());
    }

    @Override
    protected UserBO<String> convertToBo(UserDTO dto) {
        UserBO<String> result = new UserBO<String>();
        modelMapper.map(dto, result);
        result.setId(dto.getId());
        
        return result;
    }    
}
