package com.rustambek.clinic.specification;

import com.rustambek.clinic.visit.entity.Visit;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public final class VisitSpecifications {
    private VisitSpecifications() {}

    public static Specification<Visit> byFilter(
            Long patientId,
            UUID primaryDoctorId,
            VisitType visitType,
            VisitStatus status,
            LocalDateTime from,
            LocalDateTime to
    ) {
        return Specification.where(patientIdEq(patientId))
                .and(primaryDoctorIdEq(primaryDoctorId))
                .and(visitTypeEq(visitType))
                .and(statusEq(status))
                .and(visitDateFrom(from))
                .and(visitDateTo(to));
    }

    public static Specification<Visit> patientIdEq(Long patientId) {
        if (patientId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("patientId"), patientId);
    }

    public static Specification<Visit> primaryDoctorIdEq(UUID primaryDoctorId) {
        if (primaryDoctorId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("primaryDoctorId"), primaryDoctorId);
    }

    public static Specification<Visit> visitTypeEq(VisitType visitType) {
        if (visitType == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitType"), visitType);
    }

    public static Specification<Visit> statusEq(VisitStatus status) {
        if (status == null) return null;
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Visit> visitDateFrom(LocalDateTime from) {
        if (from == null) return null;
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("visitDateTime"), from);
    }

    public static Specification<Visit> visitDateTo(LocalDateTime to) {
        if (to == null) return null;
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("visitDateTime"), to);
    }
}
