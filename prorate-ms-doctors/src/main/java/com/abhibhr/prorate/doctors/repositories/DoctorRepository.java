package com.abhibhr.prorate.doctors.repositories;

import com.abhibhr.prorate.doctors.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
