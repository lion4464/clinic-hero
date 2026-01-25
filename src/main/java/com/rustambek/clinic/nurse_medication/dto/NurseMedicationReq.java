package com.rustambek.clinic.nurse_medication.dto;

import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeReq;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductOutcomeReq;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NurseMedicationReq {
    private Long visitId;
    private Long orderId;
    private Boolean onlyService;
    private List<PharmacyProductOutcomeReq> pharmacyProductOutcomes;//TODO !!! agar patient Dorilar harid qilmasa unda NULL qiymat yuborilishi kerak
    private List<MedServiceReq>  medServices;
}
