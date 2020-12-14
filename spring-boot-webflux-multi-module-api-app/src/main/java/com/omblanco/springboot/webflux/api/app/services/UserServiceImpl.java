package com.omblanco.springboot.webflux.api.app.services;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.omblanco.springboot.webflux.api.app.web.dto.UserDTO;
import com.omblanco.springboot.webflux.api.commons.services.CommonReactiveServiceImpl;
import com.omblanco.springboot.webflux.api.commons.web.dto.UserFilterDTO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserDAO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserFilterDAO;
import com.omblanco.springboot.webflux.api.model.repository.user.UserRepository;

import lombok.Builder;
import reactor.core.publisher.Mono;

/**
 * Implmentación del servicio de usuarios
 * @author oscar.martinezblanco
 *
 */
@Service
public class UserServiceImpl extends CommonReactiveServiceImpl<UserDTO, UserDAO<Long>, UserRepository<Long>, Long> implements UserService {

    private ModelMapper modelMapper;
    
    private BCryptPasswordEncoder passwordEncoder;

    @Builder
    public UserServiceImpl(UserRepository<Long> repository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        super(repository);
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Page<UserDTO>> findByFilter(UserFilterDTO filter, Pageable pageable) {
        return repository.findAll(convertFilterToDao(filter), pageable)
                .map(this::convertPageToDto);
    }

    @Override
    public Mono<UserDTO> save(UserDTO userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return super.save(userDto);
    }

    @Override
    public Mono<UserDTO> findByEmail(String email) {
        return repository.findByEmail(email).map(this::convertToDto);
    }
    
    /**
     * Conversión de Modelo a DTO
     * @param user Usuario DAO
     * @return Usuario DTO
     */
    @Override 
    protected UserDTO convertToDto(UserDAO<Long> user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    /**
     * Transforma una página de modelos a dtos
     * @param userPage Página de DAOS
     * @return Página de dtos
     */
    @Override 
    protected Page<UserDTO> convertPageToDto(Page<UserDAO<Long>> userPage) {
        return new PageImpl<UserDTO>(userPage.getContent().stream().map(user -> {
            return this.convertToDto(user);
        }).collect(Collectors.toList()), userPage.getPageable(), userPage.getTotalElements());
    }
    
    /**
     * Conversión de DTO a Modelo
     * @param userDto DTO del usuario
     * @return DAO del usuario
     */
    @Override
    protected UserDAO<Long> convertToEntity(UserDTO userDto) {
        
        UserDAO<Long> result = new UserDAO<Long>();
        modelMapper.map(userDto, result);
        result.setId(userDto.getId());
        
        return result;
    }
    
    /**
     * Transforma un filtro dto en un filtro dao
     * @param dto Filtro de la capa de vista
     * @return Filtro de la capa de persistencia
     */
    private UserFilterDAO convertFilterToDao(UserFilterDTO dto) {
        return modelMapper.map(dto, UserFilterDAO.class);
    }
}
