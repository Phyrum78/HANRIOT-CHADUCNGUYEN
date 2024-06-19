package com.exo2.Exercice2.controller;

import com.exo2.Exercice2.dto.PartyDto;
import com.exo2.Exercice2.entity.Party;
import com.exo2.Exercice2.repository.PartyRepository;
import com.exo2.Exercice2.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partys")
public class PartyController extends AbsGenericController<PartyDto, Party, Long, PartyRepository, PartyService> {
    public PartyController(@Autowired PartyService service) {
        super(service);
    }
}
