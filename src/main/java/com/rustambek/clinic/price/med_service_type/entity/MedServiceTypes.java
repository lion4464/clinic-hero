package com.rustambek.clinic.price.med_service_type.entity;

import com.rustambek.clinic.generic.BaseEntity;
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

    @Column(name = "price",nullable = false)
    private Long price;
}
