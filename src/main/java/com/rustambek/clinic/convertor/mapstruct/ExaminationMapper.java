package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.examination.dto.ExaminationDto;
import com.rustambek.clinic.examination.dto.ExaminationReq;
import com.rustambek.clinic.examination.entity.Examination;
import com.rustambek.clinic.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class, uses = {DoctorMapper.class, VisitMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExaminationMapper extends GenericMapper<Examination, ExaminationDto, ExaminationReq> {
}
