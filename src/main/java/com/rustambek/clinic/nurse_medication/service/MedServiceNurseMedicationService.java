package com.rustambek.clinic.nurse_medication.service;

import com.rustambek.clinic.billing.invoice.service.InvoiceService;
import com.rustambek.clinic.convertor.mapstruct.MedServiceNurseMedicationMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.nurse_medication.dto.MedServiceNurseMedicationDto;
import com.rustambek.clinic.nurse_medication.dto.MedServiceNurseMedicationReq;
import com.rustambek.clinic.nurse_medication.dto.MedServiceReq;
import com.rustambek.clinic.nurse_medication.repository.MedServiceNurseMedicationRepository;
import com.rustambek.clinic.nurse_medication.entity.MedServicePriceNurseMedication;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import com.rustambek.clinic.price.med_service_type.service.MedServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rustambek.clinic.specification.MedServiceNurseMedicationSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class MedServiceNurseMedicationService {

    private final MedServiceNurseMedicationRepository repository;
    private final MedServiceNurseMedicationMapper mapper;
    private final InvoiceService invoiceService;
    private final MedServiceTypeService medServiceTypeService;

    public MedServiceNurseMedicationDto create(MedServiceNurseMedicationReq req) {
        MedServicePriceNurseMedication entity = mapper.toEntity(req);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public MedServiceNurseMedicationDto getById(Long id) {
        MedServicePriceNurseMedication e = getModel(id);
        return mapper.toDto(e);
    }

    public MedServicePriceNurseMedication getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("MedServiceNurseMedication not found: " + id));
    }

    public MedServiceNurseMedicationDto update(Long id, MedServiceNurseMedicationReq req) {
        MedServicePriceNurseMedication e = getModel(id);
        mapper.setModel(e, req);
        return mapper.toDto(repository.save(e));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("MedServiceNurseMedication not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<MedServiceNurseMedicationDto> pageable(
            Long visitId,
            Long medServicePriceId,
            Long nurseMedicationId,
            Pageable pageable
    ) {
        return mapper.toDtoPage(repository.findAll(byFilter(visitId, medServicePriceId, nurseMedicationId), pageable));
    }

    public void createMultiple(List<MedServiceReq> medServices, Long nurseMedicationId, Long visitId) {
        List<MedServiceTypes> medServiceTypes = medServiceTypeService.findAllByIds(medServices.stream().map(MedServiceReq::getMedServiceTypeId).collect(Collectors.toSet()));
        Map<Long, MedServiceTypes> mapMedServices = medServiceTypes.stream().collect(Collectors.toMap(MedServiceTypes::getId, e ->e));
        Set<MedServicePriceNurseMedication> models = new HashSet<>();
        for (MedServiceReq medService : medServices) {
            MedServicePriceNurseMedication entity = MedServicePriceNurseMedication.builder().
                    quantity(medService.getQuantity())
                    .medServicePriceId(medService.getMedServiceTypeId())
                    .nurseMedicationId(nurseMedicationId)
                    .medServiceTypes(mapMedServices.get(medService.getMedServiceTypeId()))
                    .visitId(visitId)
                    .build();
            models.add(entity);
        }
        List<MedServicePriceNurseMedication> medServicePriceNurseMedications = repository.saveAllAndFlush(models);
        invoiceService.createInvoiceWithInvoiceItemsForNurseCares(visitId,medServicePriceNurseMedications);
    }
}

