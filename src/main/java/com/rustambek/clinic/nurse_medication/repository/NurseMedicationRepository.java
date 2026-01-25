package com.rustambek.clinic.nurse_medication.repository;

import com.rustambek.clinic.nurse_medication.entity.NurseMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NurseMedicationRepository extends JpaRepository<NurseMedication, Long>, JpaSpecificationExecutor<NurseMedication> {
    @Query("""
           select nm from NurseMedication nm
           left join fetch nm.pharmacyProductOutcomes ppo
           where nm.id = :id
           """)
    Optional<NurseMedication> findByIdWithOutcomes(@Param("id") Long id);

}
