package com.rustambek.clinic.billing.outcome.dto;

import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeReq {
    private Long outcomeTypeId;
    private Long amount;
}
