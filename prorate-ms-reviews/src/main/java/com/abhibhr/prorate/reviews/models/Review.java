package com.abhibhr.prorate.reviews.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private Long id;
    private Long doctorId;
    private Long reviewerId;
    private String content;
    private Integer rating;
}
