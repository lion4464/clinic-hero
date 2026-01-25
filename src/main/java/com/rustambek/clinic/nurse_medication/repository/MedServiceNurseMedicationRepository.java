package com.rustambek.clinic.nurse_medication.repository;

import com.rustambek.clinic.nurse_medication.entity.MedServicePriceNurseMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedServiceNurseMedicationRepository
        extends JpaRepository<MedServicePriceNurseMedication, Long>,
        JpaSpecificationExecutor<MedServicePriceNurseMedication> {
}
