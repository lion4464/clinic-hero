package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.billing.outcome.dto.OutcomeDto;
import com.rustambek.clinic.billing.outcome.dto.OutcomeReq;
import com.rustambek.clinic.billing.outcome.entity.Outcome;
import com.rustambek.clinic.config.GlobalMapperConfig;
import com.rustambek.clinic.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",config = GlobalMapperConfig.class,uses = {OutcomeTypeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OutcomeMapper extends GenericMapper<Outcome, OutcomeDto, OutcomeReq> {
}
