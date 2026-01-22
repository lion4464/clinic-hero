package com.rustambek.clinic.visit.service;

import com.rustambek.clinic.convertor.mapstruct.VisitMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.visit.dto.VisitDto;
import com.rustambek.clinic.visit.dto.VisitReq;
import com.rustambek.clinic.visit.entity.Visit;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import com.rustambek.clinic.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.rustambek.clinic.specification.VisitSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class VisitService {

    private final VisitRepository repository;
    private final VisitMapper mapper;

    public VisitDto create(VisitReq req) {
        Visit entity = mapper.toEntity(req);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public VisitDto getById(Long id) {
        Visit visit = getModel(id);
        return mapper.toDto(visit);
    }

    public Visit getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Visit not found: " + id));
    }

    public VisitDto update(Long id, VisitReq req) {
        Visit visit = getModel(id);
        mapper.setModel(visit, req);
        return mapper.toDto(repository.save(visit));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Visit not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<VisitDto> pageable(
            Long patientId,
            UUID primaryDoctorId,
            VisitType visitType,
            VisitStatus status,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable
    ) {
        return mapper.toDtoPage(repository.findAll(byFilter(patientId, primaryDoctorId, visitType, status, from, to), pageable));
    }
}
