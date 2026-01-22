package com.rustambek.clinic.visit.dto;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VisitDto {
    private Long id;
    private PatientDto patient;
    private LocalDateTime visitDateTime;
    private VisitType visitType;
    private VisitStatus status;
    private DoctorDto primaryDoctor;
    private String note;
}
