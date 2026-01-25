package com.rustambek.clinic.nurse_medication.controller;

import com.rustambek.clinic.nurse_medication.dto.MedServiceNurseMedicationDto;
import com.rustambek.clinic.nurse_medication.dto.MedServiceNurseMedicationReq;
import com.rustambek.clinic.nurse_medication.service.MedServiceNurseMedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/med-services")
@RequiredArgsConstructor
public class MedServiceNurseMedicationController {

    private final MedServiceNurseMedicationService service;

    @PostMapping
    public MedServiceNurseMedicationDto create(@RequestBody MedServiceNurseMedicationReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public MedServiceNurseMedicationDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public MedServiceNurseMedicationDto update(@PathVariable Long id, @RequestBody MedServiceNurseMedicationReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping("/pageable")
    public Page<MedServiceNurseMedicationDto> pageable(
            @RequestParam(required = false) Long visitId,
            @RequestParam(required = false) Long medServicePriceId,
            @RequestParam(required = false) Long nurseMedicationId,
            Pageable pageable
    ) {
        return service.pageable(visitId, medServicePriceId, nurseMedicationId, pageable);
    }
}
