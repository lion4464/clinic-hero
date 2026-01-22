package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceDto;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceReq;
import com.rustambek.clinic.price.doctor_price.entity.DoctorPrice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorPriceMapper extends GenericMapper<DoctorPrice, DoctorPriceDto, DoctorPriceReq> {
}
