package com.rustambek.clinic.billing.outcome_type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutcomeTypeDto {
    private Long id;
    private String name;
}

