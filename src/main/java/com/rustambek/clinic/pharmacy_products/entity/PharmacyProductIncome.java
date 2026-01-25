package com.rustambek.clinic.pharmacy_products.entity;

import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pharmacy_product_income")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyProductIncome extends BaseEntity {
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

    public static PharmacyProductIncome builsModel(Long pharmacyProductId,Integer quantity,Long salePrice,Long incomePrice) {
        return PharmacyProductIncome.builder()
                .pharmacyProductId(pharmacyProductId)
                .incomePrice(incomePrice)
                .quantity(quantity)
                .salePrice(salePrice).build();
    }
}
