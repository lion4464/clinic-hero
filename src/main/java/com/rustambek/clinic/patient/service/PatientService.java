package com.rustambek.clinic.patient.service;

import com.rustambek.clinic.convertor.mapstruct.PatientMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.dto.PatientReq;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static com.rustambek.clinic.specification.PatientSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientDto create(PatientReq req) {
        Patient entity = mapper.toEntity(req);
        entity.setId(null);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public PatientDto getById(Long id) {
        Patient patient = getModel(id);
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
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<PatientDto> pageable(String fullName, String city, String region, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(fullName, city, region), pageable));
    }
}
