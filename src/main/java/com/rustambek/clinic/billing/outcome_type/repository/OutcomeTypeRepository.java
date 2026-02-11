package com.rustambek.clinic.billing.outcome_type.repository;

import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeTypeRepository extends JpaRepository<OutcomeType, Long> {
    Page<OutcomeType> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
