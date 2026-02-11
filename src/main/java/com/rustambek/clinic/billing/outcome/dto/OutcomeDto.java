package com.rustambek.clinic.billing.outcome.dto;

import com.rustambek.clinic.billing.outcome_type.dto.OutcomeTypeDto;
import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeDto {
    private Long id;
    private OutcomeTypeDto outcomeType;
    private Long amount;
}
