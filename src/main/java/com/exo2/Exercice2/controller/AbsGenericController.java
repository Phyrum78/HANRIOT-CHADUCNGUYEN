package com.exo2.Exercice2.controller;

import com.exo2.Exercice2.dto.AbsDto;
import com.exo2.Exercice2.exception.EntityNotFoundExecption;
import com.exo2.Exercice2.service.AbsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
public abstract class AbsGenericController<Dto extends AbsDto<ID>, Entity, ID, Repository extends JpaRepository<Entity, ID>, Service extends AbsService<Dto, Entity, ID, Repository>> {

    Service service;

    @GetMapping
    public ResponseEntity<List<Dto>> ndAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int amount) {
        Pageable pageable = PageRequest.of(page, amount);

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Dto> findOneById(@PathVariable ID id) {
        try {
            return ResponseEntity.ok(service.findOne(id));
        } catch (EntityNotFoundExecption exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Dto> insertOne(@RequestBody Dto dto) {
        return ResponseEntity.ok(service.insertOne(dto));
    }

    @PutMapping
    public ResponseEntity<Dto> updateOne(@RequestBody Dto dto) {
        try {
            return ResponseEntity.ok(service.updateOne(dto, dto.getId()));
        } catch (EntityNotFoundExecption exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Dto> deleteOne(@PathVariable ID id) {
        try {
            return ResponseEntity.ok(service.deleteOne(id));
        } catch (EntityNotFoundExecption exception) {
            return ResponseEntity.notFound().build();
        }
    }

}