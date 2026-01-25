package com.rustambek.clinic.specification;

import com.rustambek.clinic.nurse_medication.entity.MedServicePriceNurseMedication;
import org.springframework.data.jpa.domain.Specification;

public final class MedServiceNurseMedicationSpecifications {
    private MedServiceNurseMedicationSpecifications() {}

    public static Specification<MedServicePriceNurseMedication> byFilter(
            Long visitId,
            Long medServicePriceId,
            Long nurseMedicationId
    ) {
        return Specification.where(visitIdEq(visitId))
                .and(medServicePriceIdEq(medServicePriceId))
                .and(nurseMedicationIdEq(nurseMedicationId));
    }

    public static Specification<MedServicePriceNurseMedication> visitIdEq(Long visitId) {
        if (visitId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitId"), visitId);
    }

    public static Specification<MedServicePriceNurseMedication> medServicePriceIdEq(Long id) {
        if (id == null) return null;
        return (root, query, cb) -> cb.equal(root.get("medServicePriceId"), id);
    }

    public static Specification<MedServicePriceNurseMedication> nurseMedicationIdEq(Long id) {
        if (id == null) return null;
        return (root, query, cb) -> cb.equal(root.get("nurseMedicationId"), id);
    }
}
