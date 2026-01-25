package com.rustambek.clinic.pharmacy_products.repository;

import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyProductIncomeRepository
        extends JpaRepository<PharmacyProductIncome, Long>, JpaSpecificationExecutor<PharmacyProductIncome> {
}
