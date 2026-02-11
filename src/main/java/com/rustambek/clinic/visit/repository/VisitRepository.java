package com.rustambek.clinic.visit.repository;

import com.rustambek.clinic.visit.entity.Visit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> , JpaSpecificationExecutor<Visit> {
    List<Visit> findByPatientIdOrderByCreatedAtDesc(Long patientId, Pageable pageable);

    Boolean existsByPatientId(Long id);
}
