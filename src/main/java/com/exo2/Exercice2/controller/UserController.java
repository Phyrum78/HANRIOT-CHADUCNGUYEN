package com.exo2.Exercice2.controller;

import com.exo2.Exercice2.dto.UserDto;
import com.exo2.Exercice2.entity.Utilisateur;
import com.exo2.Exercice2.repository.UserRepository;
import com.exo2.Exercice2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbsGenericController<UserDto, Utilisateur, Long, UserRepository, UserService> {
    public UserController(@Autowired UserService service) {
        super(service);
    }
}
