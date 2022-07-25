package com.abhibhr.prorate.doctors.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "doctors")
public class DoctorEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String firstName;
    private String lastName;
    private String houseNo;
    private String address1;
    private String address2;
    private String city;
    private String dist;
    private String state;
    private String pin;
}