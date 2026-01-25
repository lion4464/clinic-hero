package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.nurse_medication.dto.NurseMedicationDto;
import com.rustambek.clinic.nurse_medication.dto.NurseMedicationReq;
import com.rustambek.clinic.nurse_medication.entity.NurseMedication;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        config = GlobalMapperConfig.class,
        uses = { PharmacyProductOutcomeMapper.class }
)
public interface NurseMedicationMapper extends GenericMapper<NurseMedication, NurseMedicationDto, NurseMedicationReq> {
    void setModel(@MappingTarget NurseMedication model, NurseMedicationReq request);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visit", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "pharmacyProductOutcomes", ignore = true)
    NurseMedication toEntity(NurseMedicationReq request);
}

