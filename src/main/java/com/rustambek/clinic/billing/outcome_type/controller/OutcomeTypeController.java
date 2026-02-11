package com.rustambek.clinic.billing.outcome_type.controller;

import com.rustambek.clinic.billing.outcome_type.dto.OutcomeTypeReq;
import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.rustambek.clinic.billing.outcome_type.service.OutcomeTypeService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/outcome-types")
@RequiredArgsConstructor
public class OutcomeTypeController {

    private final OutcomeTypeService service;

    @PostMapping
    public OutcomeType create(@RequestBody OutcomeTypeReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public OutcomeType get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OutcomeType update(@PathVariable Long id, @RequestBody OutcomeTypeReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<OutcomeType> pageable(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return service.pageable(name, pageable);
    }
}

