package com.rustambek.clinic.doctors.service;

import com.rustambek.clinic.convertor.mapstruct.DoctorMapper;
import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.dto.DoctorReq;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.doctors.repository.DoctorRepository;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.specification.DoctorSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.rustambek.clinic.specification.DoctorSpecifications.*;


@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository repository;
    private final DoctorMapper mapper;

    public DoctorDto create(DoctorReq req) {
        Doctor entity = mapper.toEntity(req);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public DoctorDto getById(UUID id) {
        Doctor doctor = getModel(id);
        return mapper.toDto(doctor);
    }

    public DoctorDto update(UUID id, DoctorReq req) {
        Doctor doctor = getModel(id);
        mapper.setModel(doctor,req);
        return mapper.toDto(repository.save(doctor));
    }

    public Doctor getModel(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Doctor not found: " + id));

    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Doctor not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<DoctorDto> pageable(String fullName, String speciality, ActiveType type, Pageable pageable) {
        Specification<Doctor> spec =  DoctorSpecifications.byFilter(fullName, speciality, type);
        return mapper.toDtoPage(repository.findAll(spec, pageable));
    }
}

