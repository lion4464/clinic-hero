package com.rustambek.clinic.specification;
import com.rustambek.clinic.examination.entity.Examination;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class ExaminationSpecifications {
    private ExaminationSpecifications() {}

    public static Specification<Examination> byFilter(Long visitId, UUID doctorId) {
        return Specification.where(visitIdEq(visitId))
                .and(doctorIdEq(doctorId));
    }

    public static Specification<Examination> visitIdEq(Long visitId) {
        if (visitId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitId"), visitId);
    }

    public static Specification<Examination> doctorIdEq(UUID doctorId) {
        if (doctorId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("doctorId"), doctorId);
    }
}
