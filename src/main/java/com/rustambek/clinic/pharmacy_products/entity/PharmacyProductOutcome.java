package com.rustambek.clinic.pharmacy_products.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.nurse_medication.entity.NurseMedication;
import com.rustambek.clinic.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pharmacy_product_outcome")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyProductOutcome extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pharmacy_id", nullable = false, updatable = false,insertable = false)
    private PharmacyProduct pharmacyProduct;

    @Column(name = "pharmacy_id")
    private Long pharmacyProductId;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "income_price",nullable = false)
    private Long incomePrice;

    @Column(name = "sale_price",nullable = false)
    private Long salePrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false, insertable = false, updatable = false)
    private Visit visit;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nurse_medication_id", nullable = false, insertable = false, updatable = false)
    private NurseMedication nurseMedication;

    @Column(name = "nurse_medication_id", nullable = false)
    private Long nurseMedicationId;


}
