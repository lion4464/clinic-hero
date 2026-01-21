package com.rustambek.clinic.patient.controller;

import com.rustambek.clinic.patient.dto.PatientDto;
import com.rustambek.clinic.patient.dto.PatientReq;
import com.rustambek.clinic.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public PatientDto create(@RequestBody PatientReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PatientDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PatientDto update(@PathVariable Long id, @RequestBody PatientReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

     @GetMapping
    public Page<PatientDto> pageable(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String region,
            Pageable pageable
    ) {
        return service.pageable(fullName, city, region, pageable);
    }
}
