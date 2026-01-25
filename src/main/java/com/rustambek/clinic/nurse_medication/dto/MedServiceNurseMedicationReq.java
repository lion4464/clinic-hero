package com.rustambek.clinic.nurse_medication.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedServiceNurseMedicationReq {
    private Long visitId;
    private Long medServicePriceId;
    private Long nurseMedicationId;
    private Integer quantity;
}
