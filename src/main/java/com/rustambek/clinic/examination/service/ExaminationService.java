package com.rustambek.clinic.examination.service;
import com.rustambek.clinic.billing.invoice.service.InvoiceService;
import com.rustambek.clinic.convertor.mapstruct.ExaminationMapper;
import com.rustambek.clinic.examination.dto.ExaminationDto;
import com.rustambek.clinic.examination.dto.ExaminationReq;
import com.rustambek.clinic.examination.entity.Examination;
import com.rustambek.clinic.examination.model.Status;
import com.rustambek.clinic.examination.repository.ExaminationRepository;
import com.rustambek.clinic.exception.DataNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.rustambek.clinic.specification.ExaminationSpecifications.byFilter;

@Service
@RequiredArgsConstructor
@Transactional
public class ExaminationService {

    private final ExaminationRepository repository;
    private final ExaminationMapper mapper;
    private final InvoiceService invoiceService;

    public ExaminationDto create(ExaminationReq req) {
        Examination entity = mapper.toEntity(req);
        entity.setStatus(Status.OPEN);
        Examination save = repository.saveAndFlush(entity);
        List<Examination> examinations = List.of(save);
        invoiceService.createInvoiceWithInvoiceItemsForExaminations(req.getVisitId(),examinations);
        return mapper.toDto(save);
    }

    @Transactional(readOnly = true)
    public ExaminationDto getById(Long id) {
        Examination exam = getModel(id);
        return mapper.toDto(exam);
    }

    public Examination getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Examination not found: " + id));
    }

    public ExaminationDto update(Long id, ExaminationReq req) {
        Examination exam = getModel(id);
        mapper.setModel(exam, req);
        return mapper.toDto(repository.save(exam));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Examination not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ExaminationDto> pageable(Long visitId, UUID doctorId, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(visitId, doctorId), pageable));
    }


    public List<Examination> getAllModelByVisitIdAndStatusNotPaid(Long visitId) {
        return repository.findAllByVisitIdAndStatus(visitId, Status.OPEN);
    }
}

