package com.rustambek.clinic.pharmacy_products.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.patient.entity.Patient;
import jakarta.persistence.*;

public class PharmacyProductIncome extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false, updatable = false,insertable = false)
    private Patient patient;

    @Column(name = "patient_id")
    private Long patientId;
}
