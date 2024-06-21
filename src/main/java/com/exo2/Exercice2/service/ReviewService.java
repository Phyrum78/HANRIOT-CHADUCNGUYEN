package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.ReviewDto;
import com.exo2.Exercice2.entity.Review;
import com.exo2.Exercice2.exception.EntityNotFoundExecption;
import com.exo2.Exercice2.mapper.ReviewMapper;
import com.exo2.Exercice2.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exo2.Exercice2.consts.CacheKey.findAllReview;
import static com.exo2.Exercice2.consts.CacheKey.findByIdReview;

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
    protected Review toEntity(ReviewDto ReviewDto) {
        Review response = mapper.toEntity(ReviewDto);
        return response;
    }

    @Override
    @Cacheable(value = findAllReview, key = "#pageable")
    public List<ReviewDto> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    @Cacheable(value = findByIdReview, key = "#aLong")
    public ReviewDto findOne(Long aLong) throws EntityNotFoundExecption {
        return super.findOne(aLong);
    }

    @Override
    @Caching(put = @CachePut(value = findByIdReview, key = "#aLong"),
            evict = @CacheEvict(value = findAllReview, allEntries = true))
    public ReviewDto updateOne(ReviewDto reviewDto, Long aLong) throws EntityNotFoundExecption {
        return super.updateOne(reviewDto, aLong);
    }

    @Override
    @Caching(put = @CachePut(value = findByIdReview, key = "#aLong"),
            evict = @CacheEvict(value = findAllReview, allEntries = true))
    public ReviewDto deleteOne(Long aLong) throws EntityNotFoundExecption {
        return super.deleteOne(aLong);
    }

    @Override
    @Caching(
            evict = @CacheEvict(value = findAllReview, allEntries = true))
    public ReviewDto insertOne(ReviewDto reviewDto) {
        return super.insertOne(reviewDto);
    }
}
