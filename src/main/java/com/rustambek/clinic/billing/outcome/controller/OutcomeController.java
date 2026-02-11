package com.rustambek.clinic.billing.outcome.controller;

import com.rustambek.clinic.billing.outcome.dto.OutcomeDto;
import com.rustambek.clinic.billing.outcome.dto.OutcomeReq;
import com.rustambek.clinic.billing.outcome.service.OutcomeService;
import com.rustambek.clinic.billing.outcome_type.dto.OutcomeTypeReq;
import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import com.rustambek.clinic.billing.outcome_type.service.OutcomeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/outcome")
@RequiredArgsConstructor
public class OutcomeController {

    private final OutcomeService service;

    @PostMapping
    public OutcomeDto create(@RequestBody OutcomeReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public OutcomeDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OutcomeDto update(@PathVariable Long id, @RequestBody OutcomeReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<OutcomeDto> pageable(
            @RequestParam(required = false) Long outcomeTypeId,
            @RequestParam(required = false) String createdAtFrom,
            @RequestParam(required = false) String createdAtToDate,
            Pageable pageable
    ) {
        return service.pageable(outcomeTypeId,createdAtFrom,createdAtToDate, pageable);
    }
}