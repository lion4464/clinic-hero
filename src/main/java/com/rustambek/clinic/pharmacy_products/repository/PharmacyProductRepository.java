package com.rustambek.clinic.pharmacy_products.repository;

import com.rustambek.clinic.pharmacy_products.entity.PharmacyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface PharmacyProductRepository
        extends JpaRepository<PharmacyProduct, Long>, JpaSpecificationExecutor<PharmacyProduct> {
    Long countAllByIsDeletedIsFalseAndCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}
