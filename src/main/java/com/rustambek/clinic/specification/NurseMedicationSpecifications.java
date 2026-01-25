package com.rustambek.clinic.specification;

import com.rustambek.clinic.nurse_medication.entity.NurseMedication;
import org.springframework.data.jpa.domain.Specification;

public final class NurseMedicationSpecifications {
    private NurseMedicationSpecifications() {}

    public static Specification<NurseMedication> byFilter(Long visitId, Long orderId, Boolean onlyService) {
        return Specification.where(visitIdEq(visitId))
                .and(orderIdEq(orderId))
                .and(onlyServiceEq(onlyService));
    }

    public static Specification<NurseMedication> visitIdEq(Long visitId) {
        if (visitId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitId"), visitId);
    }

    public static Specification<NurseMedication> orderIdEq(Long orderId) {
        if (orderId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("orderId"), orderId);
    }

    public static Specification<NurseMedication> onlyServiceEq(Boolean onlyService) {
        if (onlyService == null) return null;
        return (root, query, cb) -> cb.equal(root.get("onlyService"), onlyService);
    }
}
