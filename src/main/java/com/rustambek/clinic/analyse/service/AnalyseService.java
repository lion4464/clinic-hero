package com.rustambek.clinic.analyse.service;

import com.rustambek.clinic.analyse.dto.AnalyseDto;
import com.rustambek.clinic.analyse.dto.AnalyseReq;
import com.rustambek.clinic.analyse.entity.Analyse;
import com.rustambek.clinic.analyse.repository.AnalyseRepository;
import com.rustambek.clinic.convertor.mapstruct.AnalyseMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.specification.AnalyseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyseService {

    private final AnalyseRepository repository;
    private final AnalyseMapper mapper;

    public AnalyseDto create(AnalyseReq analyseReq) {
        Analyse entity = mapper.toEntity(analyseReq);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public AnalyseDto getById(UUID id) {
        Analyse entity = getModel(id);
        return mapper.toDto(entity);
    }

    public AnalyseDto update(UUID id, AnalyseReq req) {
        Analyse entity = getModel(id);
        mapper.setModel(entity, req);
        return mapper.toDto(repository.save(entity));
    }

    private Analyse getModel(UUID id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("DoctorPrice not found: " + id));
    }

    public void delete(UUID id){
        if(!repository.existsById(id)){
            throw new DataNotFoundException("DoctorPrice not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<AnalyseDto> pageable(String name, Long price, Pageable pageable){
        Specification<Analyse> specification = AnalyseSpecification.byFilter( name, price);
        return mapper.toDtoPage(repository.findAll(specification,pageable));
    }
}
