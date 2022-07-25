package com.abhibhr.prorate.reviews.controllers;

import com.abhibhr.prorate.reviews.models.Review;
import com.abhibhr.prorate.reviews.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/{doctorId}/reviews")
    public List<Review> findForDoctor(@PathVariable Long doctorId){
        return reviewService.findAllForDoctor(doctorId);
    }

    @PostMapping("/{doctorId}/reviews")
    public Review post(@PathVariable Long doctorId,@RequestBody Review review){
        System.out.println(review);
        review.setDoctorId(doctorId);
        return reviewService.addOne(review);
    }

    @DeleteMapping("/{doctorId}/reviews/{reviewId}")
    public void delete(@PathVariable Long doctorId,@PathVariable Long reviewId){
         reviewService.delete(reviewId);
    }

    @PutMapping("/{doctorId}/reviews/{reviewId}")
    public Review put(@PathVariable Long doctorId,@PathVariable Long reviewId,@RequestBody Review review){
        review.setDoctorId(doctorId);
        review.setId(reviewId);
        return reviewService.update(review);
    }

}
