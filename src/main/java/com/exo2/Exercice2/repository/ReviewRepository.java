package com.exo2.Exercice2.repository;

import com.exo2.Exercice2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRating(int rating);
}
