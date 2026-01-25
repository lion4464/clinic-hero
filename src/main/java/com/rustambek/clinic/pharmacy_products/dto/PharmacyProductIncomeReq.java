package com.rustambek.clinic.pharmacy_products.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacyProductIncomeReq {
    private Long pharmacyProductId;
    private Integer quantity;
    private Long incomePrice;
    private Long salePrice;
}
