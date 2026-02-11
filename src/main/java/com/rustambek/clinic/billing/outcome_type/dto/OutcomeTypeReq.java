package com.rustambek.clinic.billing.outcome_type.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutcomeTypeReq {

    @NotBlank
    @Size(max = 255)
    private String name;
}

