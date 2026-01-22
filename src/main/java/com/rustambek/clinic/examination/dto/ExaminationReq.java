package com.rustambek.clinic.examination.dto;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.visit.dto.VisitDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationReq {
    private Long visitId;
    private UUID doctorId;
    private String complaint;
    private String diagnosis;
    private String comment;
}
