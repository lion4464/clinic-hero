package com.rustambek.clinic.nurse_medication.dto;

import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeDto;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NurseMedicationDto {
    private Long id;
    private Long visitId;
    private Long orderId;
    private Boolean onlyService;
    private List<PharmacyProductIncomeDto> pharmacyProductOutcomes;
}
