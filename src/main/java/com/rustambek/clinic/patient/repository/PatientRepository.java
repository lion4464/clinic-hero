package com.rustambek.clinic.patient.repository;

import com.rustambek.clinic.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    Long countAllByIsDeletedIsFalseAndCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}
