package com.exo2.Exercice2.repository;

import com.exo2.Exercice2.entity.Party;
import com.exo2.Exercice2.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByLocation(String location);
}
