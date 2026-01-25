package com.rustambek.clinic.pharmacy_products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyProductOutcomeReq {
    private Long pharmacyProductId;
    private Integer quantity;
    private Long visitId;
    private Long nurseMedicationId;
}
