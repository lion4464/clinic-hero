package com.rustambek.clinic.doctors.dto;

import com.rustambek.clinic.doctors.enums.ActiveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorReq {
    private String fullName;
    private String speciality;
    private ActiveType type;
}
