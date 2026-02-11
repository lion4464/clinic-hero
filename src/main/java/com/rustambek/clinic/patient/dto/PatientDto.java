package com.rustambek.clinic.patient.dto;

import com.rustambek.clinic.visit.dto.VisitDto;
import com.rustambek.clinic.visit.dto.VisitMinDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {
    private Long id;
    private String fullName;
    private Integer age;
    private String city;
    private String region;
    private String phone;
    private List<VisitMinDto> visits;
}
