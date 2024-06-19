package com.exo2.Exercice2.mapper;

import com.exo2.Exercice2.dto.UserDto;
import com.exo2.Exercice2.entity.Utilisateur;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Utilisateur input);

    Utilisateur toEntity(UserDto input);

    List<UserDto> toDtos(List<Utilisateur> input);

    List<Utilisateur> toEntities(List<UserDto> input);
}
