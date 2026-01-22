package com.rustambek.clinic.pharmacy_products.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.pharmacy_products.model.UnitType;
import com.rustambek.clinic.visit.enums.VisitStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pharmacy_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false, length = 30)
    private UnitType unit;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "income_price",nullable = false)
    private Long incomePrice;

    @Column(name = "sale_price",nullable = false)
    private Long salePrice;

}
