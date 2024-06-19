package com.exo2.Exercice2.service;

import com.exo2.Exercice2.exception.EntityNotFoundExecption;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbsService<Dto,
        Entity,
        ID,
        Repository extends JpaRepository<Entity, ID>> {
    private final Repository repository;

    public AbsService(Repository repository) {
        this.repository = repository;
    }

    protected abstract Dto toDto(Entity entity);

    protected abstract List<Dto> toDtoList(List<Entity> entityList);

    protected abstract Entity toEntity(Dto dto);

    public List<Dto> findAll(Pageable pageable) {
        List<Entity> entityList = repository.findAll(pageable).getContent();
        return toDtoList(entityList);
    }

    public Dto findOne(ID id) throws EntityNotFoundExecption {
        Optional<Entity> one = repository.findById(id);
        if (one.isEmpty()) {
            throw new EntityNotFoundExecption();
        }
        return toDto(one.get());
    }

    public Dto insertOne(Dto dto) {
        Entity toSave = toEntity(dto);
        Entity save = repository.save(toSave);
        return toDto(save);
    }

    public Dto updateOne(Dto dto, ID id) throws EntityNotFoundExecption {
        Optional<Entity> one = repository.findById(id);
        if (one.isEmpty()) {
            throw new EntityNotFoundExecption();
        }
        Entity save = repository.save(toEntity(dto));
        return toDto(save);
    }

    public Dto deleteOne(ID id) throws EntityNotFoundExecption {
        Optional<Entity> one = repository.findById(id);
        if (one.isEmpty()) {
            throw new EntityNotFoundExecption();

        }
        repository.deleteById(id);
        return toDto(one.get());
    }
}
