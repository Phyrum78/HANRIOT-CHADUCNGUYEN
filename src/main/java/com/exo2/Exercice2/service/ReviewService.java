package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.ReviewDto;
import com.exo2.Exercice2.entity.Review;
import com.exo2.Exercice2.mapper.ReviewMapper;
import com.exo2.Exercice2.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService extends AbsService<ReviewDto, Review, Long, ReviewRepository> {
    @Autowired
    ReviewMapper mapper;

    public ReviewService(@Autowired ReviewRepository repository) {
        super(repository);
    }

    @Override
    protected ReviewDto toDto(Review review) {
        return mapper.toDto(review);
    }

    @Override
    protected List<ReviewDto> toDtoList(List<Review> reviews) {
        return mapper.toDtos(reviews);
    }

    @Override
    protected Review toEntity(ReviewDto userDto) {
        Review response = mapper.toEntity(userDto);
        return response;
    }
}
