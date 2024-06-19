package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.PartyDto;
import com.exo2.Exercice2.entity.Party;
import com.exo2.Exercice2.entity.Party;
import com.exo2.Exercice2.mapper.PartyMapper;
import com.exo2.Exercice2.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService extends AbsService<PartyDto, Party, Long, PartyRepository> {
    @Autowired
    PartyMapper mapper;

    public PartyService(@Autowired PartyRepository repository) {
        super(repository);
    }

    @Override
    protected PartyDto toDto(Party party) {
        return mapper.toDto(party);
    }

    @Override
    protected List<PartyDto> toDtoList(List<Party> partys) {
        return mapper.toDtos(partys);
    }

    @Override
    protected Party toEntity(PartyDto PartyDto) {
        Party response = mapper.toEntity(PartyDto);
        return response;
    }
}
