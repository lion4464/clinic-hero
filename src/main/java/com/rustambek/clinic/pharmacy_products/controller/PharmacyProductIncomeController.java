package com.rustambek.clinic.pharmacy_products.controller;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeReq;
import com.rustambek.clinic.pharmacy_products.service.PharmacyProductIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pharmacy-product-incomes")
@RequiredArgsConstructor
public class PharmacyProductIncomeController {

    private final PharmacyProductIncomeService service;

    @PostMapping
    public PharmacyProductIncomeDto create(@RequestBody PharmacyProductIncomeReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PharmacyProductIncomeDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PharmacyProductIncomeDto update(@PathVariable Long id, @RequestBody PharmacyProductIncomeReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<PharmacyProductIncomeDto> pageable(
            @RequestParam(required = false) Long pharmacyProductId,
            Pageable pageable
    ) {
        return service.pageable(pharmacyProductId, pageable);
    }
}
