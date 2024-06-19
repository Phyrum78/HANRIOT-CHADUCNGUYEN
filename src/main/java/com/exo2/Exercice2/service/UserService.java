package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.UserDto;
import com.exo2.Exercice2.entity.Utilisateur;
import com.exo2.Exercice2.mapper.UserMapper;
import com.exo2.Exercice2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbsService<UserDto, Utilisateur, Long, UserRepository> {
    @Autowired
    UserMapper mapper;

    public UserService(@Autowired UserRepository repository) {
        super(repository);
    }

    @Override
    protected UserDto toDto(Utilisateur utilisateur) {
        return mapper.toDto(utilisateur);
    }

    @Override
    protected List<UserDto> toDtoList(List<Utilisateur> utilisateurs) {
        return mapper.toDtos(utilisateurs);
    }

    @Override
    protected Utilisateur toEntity(UserDto userDto) {
        Utilisateur response = mapper.toEntity(userDto);
        return response;
    }
}
