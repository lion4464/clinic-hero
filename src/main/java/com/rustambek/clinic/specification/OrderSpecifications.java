package com.rustambek.clinic.specification;
import com.rustambek.clinic.order.entity.Order;
import com.rustambek.clinic.order.model.OrderStatus;
import com.rustambek.clinic.order.model.OrderType;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class OrderSpecifications {
    private OrderSpecifications() {}

    public static Specification<Order> byFilter(Long visitId, UUID createdByDoctorId, OrderType orderType, OrderStatus status) {
        return Specification.where(visitIdEq(visitId))
                .and(createdByDoctorIdEq(createdByDoctorId))
                .and(orderTypeEq(orderType))
                .and(statusEq(status));
    }

    public static Specification<Order> visitIdEq(Long visitId) {
        if (visitId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitId"), visitId);
    }

    public static Specification<Order> createdByDoctorIdEq(UUID doctorId) {
        if (doctorId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("createdByDoctorId"), doctorId);
    }

    public static Specification<Order> orderTypeEq(OrderType type) {
        if (type == null) return null;
        return (root, query, cb) -> cb.equal(root.get("orderType"), type);
    }

    public static Specification<Order> statusEq(OrderStatus status) {
        if (status == null) return null;
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }
}
