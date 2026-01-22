package com.rustambek.clinic.price.doctor_price.dto;

import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorPriceReq {
    private VisitType visitType;
    private Long price;
    private ActiveType type;
}
