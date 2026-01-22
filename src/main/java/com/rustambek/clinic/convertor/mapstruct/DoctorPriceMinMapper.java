package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceMinDto;
import com.rustambek.clinic.price.doctor_price.entity.DoctorPrice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorPriceMinMapper extends GenericAuditMapper<DoctorPrice, DoctorPriceMinDto> {
}

