package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductReq;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProduct;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PharmacyProductMapper extends GenericMapper<PharmacyProduct, PharmacyProductDto, PharmacyProductReq> {
}
