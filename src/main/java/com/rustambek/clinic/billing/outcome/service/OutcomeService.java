package com.rustambek.clinic.billing.outcome.service;

import com.rustambek.clinic.billing.outcome.dto.OutcomeDto;
import com.rustambek.clinic.billing.outcome.dto.OutcomeReq;
import com.rustambek.clinic.billing.outcome.entity.Outcome;
import com.rustambek.clinic.billing.outcome.repository.OutcomeRepository;
import com.rustambek.clinic.billing.outcome_type.dto.OutcomeTypeReq;
import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import com.rustambek.clinic.convertor.mapstruct.OutcomeMapper;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.specification.DoctorSpecifications;
import com.rustambek.clinic.specification.OutComeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OutcomeService {
     private  final OutcomeRepository outcomeRepository;
     private final OutcomeMapper outcomeMapper;

    public OutcomeDto create(OutcomeReq req) {
        Outcome outcome = outcomeMapper.toEntity(req);
        outcomeRepository.save(outcome);
        return outcomeMapper.toDto(outcome);
    }

    public OutcomeDto getById(Long id) {
        Outcome model = getModelById(id);
        return outcomeMapper.toDto(model);
    }


    private Outcome getModelById(Long id) {
        return outcomeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Out come not found"));
    }

    public OutcomeDto update(Long id, OutcomeReq req) {
        Outcome oldModel = getModelById(id);
         outcomeMapper.setModel(oldModel,req);
        return outcomeMapper.toDto(outcomeRepository.save(oldModel));
    }

    public void delete(Long id) {
        getModelById(id);
        outcomeRepository.deleteById(id);
    }

    public Page<OutcomeDto> pageable(Long outcomeTypeId, String createdAtFrom, String createdAtToDate, Pageable pageable) {
        Specification<Outcome> spec =  OutComeSpecification.byFilter(outcomeTypeId, createdAtFrom,createdAtToDate);
        return outcomeMapper.toDtoPage(outcomeRepository.findAll(spec, pageable));
    }
}
