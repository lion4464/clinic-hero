package com.rustambek.clinic.nurse_medication.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import com.rustambek.clinic.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "med_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedServicePriceNurseMedication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false, insertable = false, updatable = false)
    private Visit visit;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "med_service_price_id", nullable = false, insertable = false, updatable = false)
    private MedServiceTypes medServiceTypes;

    @Column(name = "med_service_price_id", nullable = false)
    private Long medServicePriceId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nurse_med_id", nullable = false, insertable = false, updatable = false)
    private NurseMedication nurseMedication;

    @Column(name = "nurse_med_id", nullable = false)
    private Long nurseMedicationId;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;
}
