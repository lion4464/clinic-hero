package com.rustambek.clinic.nurse_medication.dto;

import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceDto;
import com.rustambek.clinic.visit.dto.VisitDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedServiceNurseMedicationDto {
    private Long id;
    private Long visitId;
    private VisitDto visit;
    private Long medServicePriceId;
    private MedServicePriceDto medServicePrice;
    private Long nurseMedicationId;
    private NurseMedicationDto nurseMedication;
    private Integer quantity;
}
