package com.abhibhr.prorate.doctors.services;

import com.abhibhr.prorate.doctors.mapper.DoctorMapper;
import com.abhibhr.prorate.doctors.models.Doctor;
import com.abhibhr.prorate.doctors.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorMapper doctorMapper;
    public List<Doctor> findAll(){
        return doctorRepository.findAll().stream().map(doctorMapper::entityToDto).collect(Collectors.toList());
    }

    public Doctor addOne(Doctor doctor){
        return save(doctor  );
    }

    public void delete(Long id){
        doctorRepository.deleteById(id);
    }

    public Doctor update(Doctor doctor){
        return save(doctor);
    }

    public Doctor save(Doctor doctor){
        return doctorMapper.entityToDto(doctorRepository.save(doctorMapper.dtoToEntity(doctor)));
    }
}

