package com.abhibhr.prorate.doctors.mapper;

import com.abhibhr.prorate.doctors.entity.DoctorEntity;
import com.abhibhr.prorate.doctors.models.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorEntity dtoToEntity(Doctor doctor);
    Doctor entityToDto(DoctorEntity entity);
}
