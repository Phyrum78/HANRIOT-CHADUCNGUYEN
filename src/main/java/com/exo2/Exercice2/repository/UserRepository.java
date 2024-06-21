package com.exo2.Exercice2.repository;

import com.exo2.Exercice2.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByEmail(String email);
}
