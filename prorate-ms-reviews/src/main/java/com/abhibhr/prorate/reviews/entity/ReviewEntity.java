package com.abhibhr.prorate.reviews.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long doctorId;
    private Long reviewerId;
    private String content;
    private Integer rating;
}
