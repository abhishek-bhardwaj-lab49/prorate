package com.abhibhr.prorate.reviews.mapper;

import com.abhibhr.prorate.reviews.entity.ReviewEntity;
import com.abhibhr.prorate.reviews.models.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewEntity dtoToEntity(Review review);
    Review entityToDto(ReviewEntity reviewEntity);
}
