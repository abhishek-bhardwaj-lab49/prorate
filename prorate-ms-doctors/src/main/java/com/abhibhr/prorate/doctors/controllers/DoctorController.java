package com.abhibhr.prorate.doctors.controllers;

import com.abhibhr.prorate.doctors.models.Doctor;
import com.abhibhr.prorate.doctors.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAll(){
        return doctorService.findAll();
    }

    @PostMapping
    public void post(@RequestBody Doctor doctor){
        doctorService.addOne(doctor);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        doctorService.delete(id);
    }

    @PutMapping(path = "/{id}")
    public Doctor update(@RequestBody Doctor doctor,@PathVariable Long id){
        doctor.setId(id);
        return doctorService.update(doctor);
    }
}
