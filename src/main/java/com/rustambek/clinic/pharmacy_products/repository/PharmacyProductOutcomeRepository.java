package com.rustambek.clinic.pharmacy_products.repository;

import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductIncome;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyProductOutcomeRepository extends JpaRepository<PharmacyProductOutcome, Long>, JpaSpecificationExecutor<PharmacyProductOutcome> {
}
