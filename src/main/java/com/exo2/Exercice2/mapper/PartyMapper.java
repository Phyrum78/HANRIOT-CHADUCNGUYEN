package com.exo2.Exercice2.mapper;

import com.exo2.Exercice2.dto.PartyDto;
import com.exo2.Exercice2.entity.Party;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartyMapper {
    PartyDto toDto(Party input);

    Party toEntity(PartyDto input);

    List<PartyDto> toDtos(List<Party> input);

    List<Party> toEntities(List<PartyDto> input);
}
