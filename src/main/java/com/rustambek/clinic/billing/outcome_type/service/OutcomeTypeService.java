package com.rustambek.clinic.billing.outcome_type.service;

import com.rustambek.clinic.billing.outcome_type.dto.OutcomeTypeReq;
import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import com.rustambek.clinic.billing.outcome_type.repository.OutcomeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OutcomeTypeService {
    private final OutcomeTypeRepository outcomeTypeRepository;

    public OutcomeType create(OutcomeTypeReq req) {
        OutcomeType outcomeType = new OutcomeType();
        outcomeType.setName(req.getName());
        return outcomeTypeRepository.save(outcomeType);
    }

    @Transactional(readOnly = true)
    public OutcomeType getById(Long id) {
        return outcomeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OutcomeType not found: " + id));
    }
    public OutcomeType update(Long id, OutcomeTypeReq req) {
        OutcomeType outcomeType = getById(id);
        outcomeType.setName(req.getName());
        return outcomeTypeRepository.save(outcomeType);
    }
    public void delete(Long id) {
        OutcomeType outcomeType = getById(id);
        outcomeTypeRepository.delete(outcomeType);
    }
    @Transactional(readOnly = true)
    public Page<OutcomeType> pageable(String name, Pageable pageable) {
        if (name == null || name.isBlank()) {
            return outcomeTypeRepository.findAll(pageable);
        }
        return outcomeTypeRepository.findByNameContainingIgnoreCase(name.trim(), pageable);
    }

}
