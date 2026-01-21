package com.rustambek.clinic.doctors.controller;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.dto.DoctorReq;
import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @PostMapping
    public DoctorDto create(@RequestBody DoctorReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public DoctorDto get(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public DoctorDto update(@PathVariable UUID id, @RequestBody DoctorReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("pageable")
    public Page<DoctorDto> pageable(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String speciality,
            @RequestParam(required = false) ActiveType type,
            Pageable pageable
    ) {
        return service.pageable(fullName, speciality, type, pageable);
    }
}
