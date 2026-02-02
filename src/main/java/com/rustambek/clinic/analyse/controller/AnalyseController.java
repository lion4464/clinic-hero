package com.rustambek.clinic.analyse.controller;

import com.rustambek.clinic.analyse.dto.AnalyseDto;
import com.rustambek.clinic.analyse.dto.AnalyseReq;
import com.rustambek.clinic.analyse.service.AnalyseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analyse")
@RequiredArgsConstructor
public class AnalyseController {
    private final AnalyseService service;

    @PostMapping
    public AnalyseDto create(@RequestBody AnalyseReq req) {
        return service.create(req);
    }


    @GetMapping("/{id}")
    public AnalyseDto get(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AnalyseDto update(@PathVariable UUID id, @RequestBody AnalyseReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/pageable")
    public Page<AnalyseDto> pageable(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long price,
            Pageable pageable
    ) {
        return service.pageable(name, price, pageable);
    }
}
