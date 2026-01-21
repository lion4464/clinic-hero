package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.dto.DoctorReq;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.entity.Patient;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorMapper extends GenericMapper<Doctor, DoctorDto, DoctorReq> {

}
