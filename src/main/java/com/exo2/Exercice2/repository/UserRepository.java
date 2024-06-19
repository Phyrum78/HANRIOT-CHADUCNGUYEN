package com.exo2.Exercice2.repository;

import com.exo2.Exercice2.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
}
