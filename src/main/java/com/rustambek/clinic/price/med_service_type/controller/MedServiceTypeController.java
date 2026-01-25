package com.rustambek.clinic.price.med_service_type.controller;

import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceDto;
import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceReq;
import com.rustambek.clinic.price.med_service_type.service.MedServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/med-service-prices")
@RequiredArgsConstructor
public class MedServiceTypeController {

    private final MedServiceTypeService service;

    @PostMapping
    public MedServicePriceDto create(@RequestBody MedServicePriceReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public MedServicePriceDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public MedServicePriceDto update(@PathVariable Long id, @RequestBody MedServicePriceReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<MedServicePriceDto> search(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return service.search(name, pageable);
    }
}

