package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.billing.outcome_type.dto.OutcomeTypeDto;
import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import com.rustambek.clinic.generic.GenericAuditMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OutcomeTypeMapper extends GenericAuditMapper<OutcomeTypeDto, OutcomeType> {
}
