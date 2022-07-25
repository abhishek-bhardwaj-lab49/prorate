package com.abhibhr.prorate.doctors.models;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Doctor {
    private Long Id;
    private String firstName;
    private String lastName;
    private String houseNo;
    private String address1;
    private String address2;
    private String city;
    private String dist;
    private String pin;
    private String state;
}
