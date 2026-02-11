package com.rustambek.clinic.specification;

import com.rustambek.clinic.billing.invoice.entity.Invoice;
import com.rustambek.clinic.billing.invoice.model.InvoiceStatus;
import com.rustambek.clinic.patient.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvoiceSpecification {
    public static Specification<Invoice> byFilter(
            Long visitId,
            Long patientId,
            InvoiceStatus status,
            String createdAtFrom, String createdAtToDate) {
        return Specification.where(visitIdEq(visitId))
                .and(patientIdEq(patientId))
                .and(statusEq(status))
                .and(createdAtBetweenDates(createdAtFrom, createdAtToDate));
    }
    public static Specification<Invoice> createdAtBetweenDates(String fromDate, String toDate) {
        if (fromDate == null || fromDate.isBlank() || toDate == null || toDate.isBlank()) return null;

        LocalDate from = LocalDate.parse(fromDate.trim());
        LocalDate to = LocalDate.parse(toDate.trim());

        LocalDateTime start = from.atStartOfDay();
        LocalDateTime endExclusive = to.plusDays(1).atStartOfDay();

        return (root, query, cb) ->
                cb.and(
                        cb.greaterThanOrEqualTo(root.get("createdAt"), start),
                        cb.lessThan(root.get("createdAt"), endExclusive)
                );
    }
    public static Specification<Invoice> visitIdEq(Long visitId) {
        if (visitId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitId"), visitId);
    }
    public static Specification<Invoice> patientIdEq(Long patientId) {
        if (patientId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("patientId"), patientId);
    }
    public static Specification<Invoice> statusEq(InvoiceStatus status) {
        if (status == null) return null;
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }
}
