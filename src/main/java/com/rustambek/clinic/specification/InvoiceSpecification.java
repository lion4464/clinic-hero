package com.rustambek.clinic.specification;

import com.rustambek.clinic.billing.invoice.entity.Invoice;
import com.rustambek.clinic.billing.invoice.model.InvoiceStatus;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecification {
    public static Specification<Invoice> byFilter(
            Long visitId,
            Long patientId,
            InvoiceStatus status
    ) {
        return Specification.where(visitIdEq(visitId))
                .and(patientIdEq(patientId))
                .and(statusEq(status));
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
