package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.patient.dto.PatientMinDto;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.visit.dto.VisitMinDto;
import com.rustambek.clinic.visit.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, PatientMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitMinMapper extends GenericAuditMapper<Visit, VisitMinDto> {
}
