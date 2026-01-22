package com.rustambek.clinic.order.controller;
import com.rustambek.clinic.order.dto.OrderDto;
import com.rustambek.clinic.order.dto.OrderReq;
import com.rustambek.clinic.order.model.OrderStatus;
import com.rustambek.clinic.order.model.OrderType;
import com.rustambek.clinic.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderDto create(@RequestBody OrderReq req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("pageable")
    public Page<OrderDto> pageable(
            @RequestParam(required = false) Long visitId,
            @RequestParam(required = false) UUID createdByDoctorId,
            @RequestParam(required = false) OrderType orderType,
            @RequestParam(required = false) OrderStatus status,
            Pageable pageable
    ) {
        return service.pageable(visitId, createdByDoctorId, orderType, status, pageable);
    }
}
