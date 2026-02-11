package com.rustambek.clinic.price.med_service_type.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.price.med_service_type.MedServiceTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "med_service_price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedServiceTypes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MedServiceTypeEnum type;

    @Column(name = "price",nullable = false)
    private Long price;
}
