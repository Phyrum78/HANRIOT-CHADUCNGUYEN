package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.PartyDto;
import com.exo2.Exercice2.entity.Party;
import com.exo2.Exercice2.exception.EntityNotFoundExecption;
import com.exo2.Exercice2.mapper.PartyMapper;
import com.exo2.Exercice2.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exo2.Exercice2.consts.CacheKey.findAllParty;
import static com.exo2.Exercice2.consts.CacheKey.findByIdParty;

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

    @Override
    @Cacheable(value = findAllParty, key = "#pageable")
    public List<PartyDto> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    @Cacheable(value = findByIdParty, key = "#aLong")
    public PartyDto findOne(Long aLong) throws EntityNotFoundExecption {
        return super.findOne(aLong);
    }

    @Override
    @Caching(put = @CachePut(value = findByIdParty, key = "#aLong"),
            evict = @CacheEvict(value = findAllParty, allEntries = true))
    public PartyDto updateOne(PartyDto partyDto, Long aLong) throws EntityNotFoundExecption {
        return super.updateOne(partyDto, aLong);
    }

    @Override
    @Caching(put = @CachePut(value = findByIdParty, key = "#aLong"),
            evict = @CacheEvict(value = findAllParty, allEntries = true))
    public PartyDto deleteOne(Long aLong) throws EntityNotFoundExecption {
        return super.deleteOne(aLong);
    }

    @Override
    @Caching(
            evict = @CacheEvict(value = findAllParty, allEntries = true))
    public PartyDto insertOne(PartyDto partyDto) {
        return super.insertOne(partyDto);
    }
}
