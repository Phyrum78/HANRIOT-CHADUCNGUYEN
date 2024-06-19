package com.exo2.Exercice2.controller;

import com.exo2.Exercice2.dto.ReviewDto;
import com.exo2.Exercice2.entity.Review;
import com.exo2.Exercice2.repository.ReviewRepository;
import com.exo2.Exercice2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends AbsGenericController<ReviewDto, Review, Long, ReviewRepository, ReviewService> {
    public ReviewController(@Autowired ReviewService service) {
        super(service);
    }
}
