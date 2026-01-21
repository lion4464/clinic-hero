package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.visit.dto.VisitDto;
import com.rustambek.clinic.visit.dto.VisitReq;
import com.rustambek.clinic.visit.entity.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class, uses = {DoctorMapper.class, PatientMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitMapper extends GenericMapper<Visit, VisitDto, VisitReq> {
}
