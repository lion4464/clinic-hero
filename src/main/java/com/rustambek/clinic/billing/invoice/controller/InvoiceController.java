package com.rustambek.clinic.billing.invoice.controller;

import com.rustambek.clinic.billing.invoice.dto.InvoiceDto;
import com.rustambek.clinic.billing.invoice.model.InvoiceStatus;
import com.rustambek.clinic.billing.invoice.service.InvoiceService;
import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.enums.ActiveType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping("/pageable")
    public Page<InvoiceDto> pageable(
            @RequestParam(required = false) Long visitId,
            @RequestParam(required = false) InvoiceStatus status,
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) String createdAtFrom,
            @RequestParam(required = false) String createdAtToDate,
            Pageable pageable
    ) {
        return invoiceService.pageable(visitId,status,patientId,createdAtFrom,createdAtToDate, pageable);
    }
    @GetMapping("/{id}")
    public InvoiceDto get(@PathVariable("id") Long id) {
        return invoiceService.get(id);
    }
    @PutMapping("/mark_as_paid/{id}")
    public InvoiceDto markAsPaid(@PathVariable Long id) {
        return invoiceService.markAspaid(id);
    }
}
