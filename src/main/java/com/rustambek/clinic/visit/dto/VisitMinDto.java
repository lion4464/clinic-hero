package com.rustambek.clinic.visit.dto;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitMinDto {
    private Long id;
    private LocalDateTime visitDateTime;
    private VisitType visitType;
    private VisitStatus status;
    private DoctorDto primaryDoctor;
    private String note;
}
