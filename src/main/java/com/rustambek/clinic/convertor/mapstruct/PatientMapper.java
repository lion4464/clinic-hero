package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.dto.PatientReq;
import com.rustambek.clinic.patient.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",  config = GlobalMapperConfig.class,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper extends GenericMapper<Patient,PatientDto, PatientReq> {
}
