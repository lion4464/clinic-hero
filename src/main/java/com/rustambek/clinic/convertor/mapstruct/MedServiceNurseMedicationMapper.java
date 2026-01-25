package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.nurse_medication.dto.MedServiceNurseMedicationDto;
import com.rustambek.clinic.nurse_medication.dto.MedServiceNurseMedicationReq;
import com.rustambek.clinic.nurse_medication.entity.NurseMedication;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import com.rustambek.clinic.nurse_medication.entity.MedServicePriceNurseMedication;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class,uses = {VisitMapper.class, MedServiceTypes.class, NurseMedication.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedServiceNurseMedicationMapper
        extends GenericMapper<MedServicePriceNurseMedication, MedServiceNurseMedicationDto, MedServiceNurseMedicationReq> {

}

