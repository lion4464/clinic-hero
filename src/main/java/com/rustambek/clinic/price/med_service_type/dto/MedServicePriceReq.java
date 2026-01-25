package com.rustambek.clinic.price.med_service_type.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedServicePriceReq {
    private String name;
    private Long price;
}