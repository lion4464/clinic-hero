package com.rustambek.clinic.visit.dto;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VisitReq {
    private Long patientId;
    private LocalDateTime visitDateTime;
    private VisitType visitType;
    private VisitStatus status;
    private UUID primaryDoctor;
    private String note;
}
