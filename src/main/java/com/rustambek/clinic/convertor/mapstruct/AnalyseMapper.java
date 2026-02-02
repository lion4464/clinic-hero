package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.analyse.dto.AnalyseDto;
import com.rustambek.clinic.analyse.dto.AnalyseReq;
import com.rustambek.clinic.analyse.entity.Analyse;
import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.dto.DoctorReq;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalyseMapper extends GenericMapper<Analyse, AnalyseDto, AnalyseReq> {
}
