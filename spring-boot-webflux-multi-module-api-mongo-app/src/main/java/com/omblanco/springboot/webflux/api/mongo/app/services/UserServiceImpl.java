package com.omblanco.springboot.webflux.api.mongo.app.services;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.omblanco.springboot.webflux.api.commons.services.CommonReactiveServiceImpl;
import com.omblanco.springboot.webflux.api.commons.web.dto.UserFilterDTO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserDAO;
import com.omblanco.springboot.webflux.api.model.entity.user.UserFilterDAO;
import com.omblanco.springboot.webflux.api.model.repository.user.UserRepository;
import com.omblanco.springboot.webflux.api.mongo.app.web.dtos.UserDTO;

import lombok.Builder;
import reactor.core.publisher.Mono;

/**
 * Implmentación del servicio de usuarios
 * @author oscar.martinezblanco
 *
 */
@Service
public class UserServiceImpl extends CommonReactiveServiceImpl<UserDTO, UserDAO<String>, UserRepository<String>, String> implements UserService {

    private ModelMapper modelMapper;
    
    private BCryptPasswordEncoder passwordEncoder;
        
    
    @Builder
    public UserServiceImpl(UserRepository<String> repository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        super(repository);
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Mono<UserDTO> save(UserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return super.save(dto);
    }

    @Override
    public Mono<Page<UserDTO>> findByFilter(UserFilterDTO filter, Pageable pageable) {
        return repository.findAll(convertFilterToDao(filter), pageable)
                .map(this::convertPageToDto);
    }

    @Override
    public Mono<UserDTO> findByEmail(String email) {
        return repository.findByEmail(email).map(this::convertToDto);
    }

    @Override
    protected UserDTO convertToDto(UserDAO<String> entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    protected Page<UserDTO> convertPageToDto(Page<UserDAO<String>> entityPage) {
      return new PageImpl<UserDTO>(entityPage.getContent().stream().map(user -> {
          return this.convertToDto(user);
      }).collect(Collectors.toList()), entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Override
    protected UserDAO<String> convertToEntity(UserDTO dto) {
        Type userType = new TypeToken<UserDAO<String>>() {}.getType();
        return modelMapper.map(dto, userType);
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
