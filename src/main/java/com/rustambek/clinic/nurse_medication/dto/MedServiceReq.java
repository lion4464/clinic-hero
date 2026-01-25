package com.rustambek.clinic.nurse_medication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedServiceReq {
    private Long medServiceTypeId;
    private Integer quantity;
}
