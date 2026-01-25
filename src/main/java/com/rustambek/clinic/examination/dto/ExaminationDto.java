package com.rustambek.clinic.examination.dto;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.examination.model.Status;
import com.rustambek.clinic.visit.dto.VisitDto;
import com.rustambek.clinic.visit.entity.Visit;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDto {
    private Long id;
    private VisitDto visit;
    private DoctorDto doctor;
    private String complaint;
    private String diagnosis;
    private Status status;
    private VisitType visitType;
    private String comment;
}
