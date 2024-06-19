package com.exo2.Exercice2.repository;

import com.exo2.Exercice2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
