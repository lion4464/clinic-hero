package com.rustambek.clinic.specification;

import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductOutcome;
import org.springframework.data.jpa.domain.Specification;

public class PharmacyProductOutcomeSpecifications {
    public static Specification<PharmacyProductOutcome> byFilter(Long pharmacyProductId) {
        return Specification.where(pharmacyProductIdEq(pharmacyProductId));
    }

    public static Specification<PharmacyProductOutcome> pharmacyProductIdEq(Long pharmacyProductId) {
        if (pharmacyProductId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("pharmacyProductId"), pharmacyProductId);
    }
}
