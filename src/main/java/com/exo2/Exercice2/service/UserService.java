package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.UserDto;
import com.exo2.Exercice2.entity.Utilisateur;
import com.exo2.Exercice2.exception.EntityNotFoundExecption;
import com.exo2.Exercice2.mapper.UserMapper;
import com.exo2.Exercice2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exo2.Exercice2.consts.CacheKey.findAllUser;
import static com.exo2.Exercice2.consts.CacheKey.findByIdUser;

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

    @Override
    @Cacheable(value = findAllUser, key = "#pageable")
    public List<UserDto> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    @Cacheable(value = findByIdUser, key = "#aLong")
    public UserDto findOne(Long aLong) throws EntityNotFoundExecption {
        return super.findOne(aLong);
    }

    @Override
    @Caching(put = @CachePut(value = findByIdUser, key = "#aLong"),
            evict = @CacheEvict(value = findAllUser, allEntries = true))
    public UserDto updateOne(UserDto userDto, Long aLong) throws EntityNotFoundExecption {
        return super.updateOne(userDto, aLong);
    }

    @Override
    @Caching(put = @CachePut(value = findByIdUser, key = "#aLong"),
            evict = @CacheEvict(value = findAllUser, allEntries = true))
    public UserDto deleteOne(Long aLong) throws EntityNotFoundExecption {
        return super.deleteOne(aLong);
    }

    @Override
    @Caching(
            evict = @CacheEvict(value = findAllUser, allEntries = true))
    public UserDto insertOne(UserDto userDto) {
        return super.insertOne(userDto);
    }
}
