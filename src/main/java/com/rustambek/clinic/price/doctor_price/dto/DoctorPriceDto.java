package com.rustambek.clinic.price.doctor_price.dto;

import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPriceDto {
    private UUID id;
    private VisitType visitType;
    private Long price;
    private ActiveType type;
}