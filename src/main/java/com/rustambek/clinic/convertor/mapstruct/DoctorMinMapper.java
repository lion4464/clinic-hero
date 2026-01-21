package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.doctors.dto.DoctorMinDto;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.patient.dto.PatientMinDto;
import com.rustambek.clinic.patient.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorMinMapper extends GenericAuditMapper<Doctor, DoctorMinDto> {
}
