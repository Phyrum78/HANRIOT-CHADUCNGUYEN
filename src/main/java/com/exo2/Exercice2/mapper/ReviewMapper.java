package com.exo2.Exercice2.mapper;

import com.exo2.Exercice2.dto.ReviewDto;
import com.exo2.Exercice2.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ReviewMapper {
    ReviewDto toDto(Review input);

    Review toEntity(ReviewDto input);

    List<ReviewDto> toDtos(List<Review> input);

    List<Review> toEntities(List<ReviewDto> input);
}
