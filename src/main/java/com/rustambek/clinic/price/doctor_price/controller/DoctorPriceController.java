package com.rustambek.clinic.price.doctor_price.controller;

import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceDto;
import com.rustambek.clinic.price.doctor_price.dto.DoctorPriceReq;
import com.rustambek.clinic.price.doctor_price.service.DoctorPriceService;
import com.rustambek.clinic.visit.enums.VisitType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctor_price")
@RequiredArgsConstructor
public class DoctorPriceController {

    private final DoctorPriceService service;

    public DoctorPriceDto create(@RequestBody DoctorPriceReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public DoctorPriceDto get(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public DoctorPriceDto update(@PathVariable UUID id, @RequestBody DoctorPriceReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<DoctorPriceDto> pageable(
            @RequestParam(required = false) VisitType visitType,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) ActiveType type,
            Pageable pageable
    ) {
        return service.pageable(visitType, price, type, pageable);
    }
}

