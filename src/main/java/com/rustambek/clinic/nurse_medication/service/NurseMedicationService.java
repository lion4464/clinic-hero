package com.rustambek.clinic.nurse_medication.service;

import com.rustambek.clinic.convertor.mapstruct.NurseMedicationMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.nurse_medication.dto.NurseMedicationDto;
import com.rustambek.clinic.nurse_medication.dto.NurseMedicationReq;
import com.rustambek.clinic.nurse_medication.entity.NurseMedication;
import com.rustambek.clinic.nurse_medication.repository.NurseMedicationRepository;
import com.rustambek.clinic.pharmacy_products.service.PharmacyProductOutcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rustambek.clinic.specification.NurseMedicationSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class NurseMedicationService {

    private final NurseMedicationRepository repository;
    private final NurseMedicationMapper mapper;
    private final PharmacyProductOutcomeService pharmacyProductOutcomeService;
    private final MedServiceNurseMedicationService medServiceNurseMedicationService;

    public NurseMedicationDto create(NurseMedicationReq req) {
        NurseMedication entity = mapper.toEntity(req);
        NurseMedication save = repository.saveAndFlush(entity);
        if (req.getPharmacyProductOutcomes()!=null) {
            pharmacyProductOutcomeService.createMultiple(req.getPharmacyProductOutcomes(),save.getId());
            save.setOnlyService(false);
        }else
            save.setOnlyService(true);
        if (req.getMedServices()!=null)
           medServiceNurseMedicationService.createMultiple(req.getMedServices(),save.getId(),req.getVisitId());

        return mapper.toDto(repository.save(save));
    }

    @Transactional(readOnly = true)
    public NurseMedicationDto getById(Long id) {
        NurseMedication nm = getModel(id);
        return mapper.toDto(nm);
    }

    public NurseMedication getModel(Long id) {
        return repository.findByIdWithOutcomes(id)
                .orElseThrow(() -> new DataNotFoundException("NurseMedication not found: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("NurseMedication not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<NurseMedicationDto> search(Long visitId, Long orderId, Boolean onlyService, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(visitId, orderId, onlyService), pageable));
    }
}
