package com.rustambek.clinic.patient.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientReq {
    private String fullName;
    private Integer age;
    private String city;
    private String region;
    private String phone;
}
