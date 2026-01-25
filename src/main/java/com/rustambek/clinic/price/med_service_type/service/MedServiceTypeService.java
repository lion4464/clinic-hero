package com.rustambek.clinic.price.med_service_type.service;

import com.rustambek.clinic.convertor.mapstruct.MedServicePriceMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceDto;
import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceReq;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import com.rustambek.clinic.price.med_service_type.repository.MedServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.rustambek.clinic.specification.MedServicePriceSpecifications.byFilter;

@Service
@RequiredArgsConstructor
@Transactional
public class MedServiceTypeService {

    private final MedServiceTypeRepository repository;
    private final MedServicePriceMapper mapper;

    public MedServicePriceDto create(MedServicePriceReq req) {
        MedServiceTypes entity = mapper.toEntity(req);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public MedServicePriceDto getById(Long id) {
        MedServiceTypes msp = getByModel(id);
        return mapper.toDto(msp);
    }

    private MedServiceTypes getByModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("MedServiceTypes not found: " + id));
    }

    public MedServicePriceDto update(Long id, MedServicePriceReq req) {
        MedServiceTypes msp = getByModel(id);
        mapper.setModel(msp, req);
        return mapper.toDto(repository.save(msp));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("MedServiceTypes not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<MedServicePriceDto> search(String name, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(name), pageable));
    }

    public List<MedServiceTypes> findAllByIds(Set<Long> ids) {
        return repository.findAllById(ids);
    }
}

