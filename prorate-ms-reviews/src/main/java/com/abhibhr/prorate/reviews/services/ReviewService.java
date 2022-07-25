package com.abhibhr.prorate.reviews.services;


import com.abhibhr.prorate.reviews.mapper.ReviewMapper;
import com.abhibhr.prorate.reviews.models.Review;
import com.abhibhr.prorate.reviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewMapper reviewMapper;

    public List<Review> findAll(){
        return reviewRepository.findAll().stream().map(reviewMapper::entityToDto).collect(Collectors.toList());
    }

    public List<Review> findAllForDoctor(Long doctorId){
        return reviewRepository.findByDoctorId(doctorId)
                .stream().map(reviewMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Review addOne(Review review){
        return save(review);
    }

    public void delete(Long id){
        reviewRepository.deleteById(id);
    }

    public Review update(Review review){
        return save(review);
    }

    public Review save(Review review){
        return reviewMapper.entityToDto(reviewRepository.save(reviewMapper.dtoToEntity(review)));
    }
}
