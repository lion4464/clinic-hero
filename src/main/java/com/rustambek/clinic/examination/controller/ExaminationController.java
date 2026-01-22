package com.rustambek.clinic.examination.controller;
import com.rustambek.clinic.examination.dto.ExaminationDto;
import com.rustambek.clinic.examination.dto.ExaminationReq;
import com.rustambek.clinic.examination.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/examinations")
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService service;

    @PostMapping
    public ExaminationDto create(@RequestBody ExaminationReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public ExaminationDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ExaminationDto update(@PathVariable Long id, @RequestBody ExaminationReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping("/pageable")
    public Page<ExaminationDto> search(
            @RequestParam(required = false) Long visitId,
            @RequestParam(required = false) UUID doctorId,
            Pageable pageable
    ) {
        return service.pageable(visitId, doctorId, pageable);
    }
}
