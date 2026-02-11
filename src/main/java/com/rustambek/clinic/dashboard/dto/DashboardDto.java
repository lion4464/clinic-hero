package com.rustambek.clinic.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DashboardDto {
    private Long totalPatients;
    private Long totalDoctors;
    private Long totalIncomes;
    private Long totalPharmacyProducts;
}
