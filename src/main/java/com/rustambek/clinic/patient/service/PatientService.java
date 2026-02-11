package com.rustambek.clinic.patient.service;

import com.rustambek.clinic.convertor.mapstruct.PatientMapper;
import com.rustambek.clinic.convertor.mapstruct.PatientMinMapper;
import com.rustambek.clinic.exception.BadRequestException;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.dto.PatientMinDto;
import com.rustambek.clinic.patient.dto.PatientReq;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.patient.repository.PatientRepository;
import com.rustambek.clinic.visit.entity.Visit;
import com.rustambek.clinic.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.rustambek.clinic.specification.PatientSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final VisitRepository visitRepository;
    private final PatientMinMapper patientMinMapper;


    public PatientDto create(PatientReq req) {
        Patient entity = mapper.toEntity(req);
        entity.setId(null);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public PatientDto getById(Long id) {
        Patient patient = getModel(id);
        List<Visit> top3Visits = visitRepository.findByPatientIdOrderByCreatedAtDesc(id, PageRequest.of(0, 3));
        patient.setVisits(top3Visits);
        return mapper.toDto(patient);
    }

    public Patient getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Patient not found: " + id));
    }

    public PatientDto update(Long id, PatientReq req) {
        Patient patient = getModel(id);
        mapper.setModel(patient, req);
        return mapper.toDto(repository.save(patient));
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Patient not found: " + id);
        }
        Boolean existsInVisit = visitRepository.existsByPatientId(id);
        if (existsInVisit) {
            throw new BadRequestException("Visits exists this patient id:" + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<PatientMinDto> pageable(String fullName, String city, String region, String createdAtFromDate, String createdAtToDate, Pageable pageable) {
        return patientMinMapper.toDtoPage(repository.findAll(byFilter(fullName, city, region,createdAtFromDate,createdAtToDate), pageable));
    }

    public Long countByDeleteFalse(LocalDateTime from, LocalDateTime to) {
        return repository.countAllByIsDeletedIsFalseAndCreatedAtBetween(from, to);
    }
}
