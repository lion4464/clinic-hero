package com.rustambek.clinic.order.service;
import com.rustambek.clinic.convertor.mapstruct.OrderMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.order.dto.OrderDto;
import com.rustambek.clinic.order.dto.OrderReq;
import com.rustambek.clinic.order.entity.Order;
import com.rustambek.clinic.order.model.OrderStatus;
import com.rustambek.clinic.order.model.OrderType;
import com.rustambek.clinic.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.rustambek.clinic.specification.OrderSpecifications.byFilter;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderDto create(OrderReq req) {
        Order entity = mapper.toEntity(req);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public OrderDto getById(Long id) {
        Order order = getModel(id);
        return mapper.toDto(order);
    }

    public Order getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Order not found: " + id));
    }

    public OrderDto update(Long id, OrderReq req) {
        Order order = getModel(id);
        mapper.setModel(order, req);
        return mapper.toDto(repository.save(order));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Order not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<OrderDto> pageable(Long visitId, UUID createdByDoctorId, OrderType orderType, OrderStatus status, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(visitId, createdByDoctorId, orderType, status), pageable));
    }
}
