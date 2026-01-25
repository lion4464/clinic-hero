package com.rustambek.clinic.nurse_medication.controller;

import com.rustambek.clinic.nurse_medication.dto.NurseMedicationDto;
import com.rustambek.clinic.nurse_medication.dto.NurseMedicationReq;
import com.rustambek.clinic.nurse_medication.service.NurseMedicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "nurse-service-controller", description = "Record medical-care or pills")
@RequestMapping("/api/v1/nurse-service")
@RequiredArgsConstructor
public class NurseMedicationController {

    private final NurseMedicationService service;

    @PostMapping
    public NurseMedicationDto create(@RequestBody NurseMedicationReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public NurseMedicationDto get(@PathVariable Long id) {
        return service.getById(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

     @GetMapping("/pageable")
    public Page<NurseMedicationDto> search(
            @RequestParam(required = false) Long visitId,
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) Boolean onlyService,
            Pageable pageable
    ) {
        return service.search(visitId, orderId, onlyService, pageable);
    }
}
