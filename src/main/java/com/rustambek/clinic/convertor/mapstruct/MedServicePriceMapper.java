package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceDto;
import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceReq;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",config = GlobalMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedServicePriceMapper extends GenericMapper<MedServiceTypes, MedServicePriceDto, MedServicePriceReq> {
}
