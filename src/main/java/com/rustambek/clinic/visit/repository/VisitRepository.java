package com.rustambek.clinic.visit.repository;

import com.rustambek.clinic.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
