package com.rustambek.clinic.nurse_medication.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.order.entity.Order;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductOutcome;
import com.rustambek.clinic.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nurse_medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NurseMedication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false, insertable = false, updatable = false)
    private Visit visit;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @Column(name = "order_id")
    private Long orderId;

    @OneToMany(mappedBy = "nurseMedication", fetch = FetchType.LAZY)
    @Builder.Default
    private List<PharmacyProductOutcome> pharmacyProductOutcomes = new ArrayList<>();

    @OneToMany(mappedBy = "nurseMedication", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MedServicePriceNurseMedication> medServicePriceNurseMedications = new ArrayList<>();

    @Column(name = "only_service",nullable = false)
    private Boolean onlyService;


}
