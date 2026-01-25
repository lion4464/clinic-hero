package com.rustambek.clinic.pharmacy_products.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacyProductIncomeDto {
    private Long id;
    private Long pharmacyProductId;
    private PharmacyProductDto pharmacyProduct;
    private Integer quantity;
    private Long incomePrice;
    private Long salePrice;
}
