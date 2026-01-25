package com.rustambek.clinic.price.doctor_price.service;

import com.rustambek.clinic.convertor.mapstruct.DoctorPriceMapper;
import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceDto;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceReq;
import com.rustambek.clinic.price.doctor_price.entity.DoctorPrice;
import com.rustambek.clinic.price.doctor_price.repository.DoctorPriceRepository;
import com.rustambek.clinic.specification.DoctorPriceSpecifications;
import com.rustambek.clinic.visit.enums.VisitType;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorPriceService {

    private final DoctorPriceRepository repository;
    private final DoctorPriceMapper mapper;

    public DoctorPriceDto create(DoctorPriceReq doctorPriceReq) {
        DoctorPrice entity = mapper.toEntity(doctorPriceReq);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public DoctorPriceDto getById(UUID id) {
        DoctorPrice entity = getModel(id);
        return mapper.toDto(entity);
    }

    public DoctorPriceDto update(UUID id, DoctorPriceReq req) {
        DoctorPrice entity = getModel(id);
        mapper.setModel(entity, req);
        return mapper.toDto(repository.save(entity));
    }

    private DoctorPrice getModel(UUID id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("DoctorPrice not found: " + id));
    }

    public void delete(UUID id){
        if(!repository.existsById(id)){
            throw new DataNotFoundException("DoctorPrice not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<DoctorPriceDto> pageable(VisitType visitType, Long price, ActiveType activeType, Pageable pageable){
        Specification<DoctorPrice> specification = DoctorPriceSpecifications.byFilter(visitType, price, activeType);
        return mapper.toDtoPage(repository.findAll(specification,pageable));
    }

}
