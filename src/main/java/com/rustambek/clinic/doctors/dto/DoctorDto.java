package com.rustambek.clinic.doctors.dto;

import com.rustambek.clinic.doctors.enums.ActiveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private UUID id;
    private String fullName;
    private String speciality;
    private ActiveType type;
}
