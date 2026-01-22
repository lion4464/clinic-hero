package com.rustambek.clinic.visit.controller;
import com.rustambek.clinic.visit.dto.VisitDto;
import com.rustambek.clinic.visit.dto.VisitReq;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import com.rustambek.clinic.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService service;

    @PostMapping
    public VisitDto create(@RequestBody VisitReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public VisitDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public VisitDto update(@PathVariable Long id, @RequestBody VisitReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

     @GetMapping
    public Page<VisitDto> pageable(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) UUID primaryDoctorId,
            @RequestParam(required = false) VisitType visitType,
            @RequestParam(required = false) VisitStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            Pageable pageable
    ) {
        return service.pageable(patientId, primaryDoctorId, visitType, status, from, to, pageable);
    }
}
