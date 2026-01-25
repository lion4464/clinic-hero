package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeReq;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductIncome;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {PharmacyProductMapper.class},config = GlobalMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PharmacyProductIncomeMapper
        extends GenericMapper<PharmacyProductIncome, PharmacyProductIncomeDto, PharmacyProductIncomeReq> {


}
