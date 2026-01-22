package com.rustambek.clinic.pharmacy_products.controller;

import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductReq;
import com.rustambek.clinic.pharmacy_products.model.UnitType;
import com.rustambek.clinic.pharmacy_products.service.PharmacyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pharmacy-products")
@RequiredArgsConstructor
public class PharmacyProductController {

    private final PharmacyProductService service;

    @PostMapping
    public PharmacyProductDto create(@RequestBody PharmacyProductReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PharmacyProductDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PharmacyProductDto update(@PathVariable Long id, @RequestBody PharmacyProductReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<PharmacyProductDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) UnitType unit,
            Pageable pageable
    ) {
        return service.pageable(name, unit, pageable);
    }
}
