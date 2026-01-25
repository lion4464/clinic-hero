package com.rustambek.clinic.price.med_service_type.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedServicePriceDto {
    private Long id;
    private String name;
    private Long price;
}

