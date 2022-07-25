package com.abhibhr.prorate.reviews.repositories;


import com.abhibhr.prorate.reviews.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByDoctorId(Long id);
}