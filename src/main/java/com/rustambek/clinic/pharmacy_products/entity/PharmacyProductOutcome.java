package com.rustambek.clinic.pharmacy_products.entity;

import com.rustambek.clinic.generic.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PharmacyProductOutcome extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
