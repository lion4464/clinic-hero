package com.rustambek.clinic.specification;

import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductIncome;
import org.springframework.data.jpa.domain.Specification;

public final class PharmacyProductIncomeSpecifications {
    private PharmacyProductIncomeSpecifications() {}

    public static Specification<PharmacyProductIncome> byFilter(Long pharmacyProductId) {
        return Specification.where(pharmacyProductIdEq(pharmacyProductId));
    }

    public static Specification<PharmacyProductIncome> pharmacyProductIdEq(Long pharmacyProductId) {
        if (pharmacyProductId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("pharmacyProductId"), pharmacyProductId);
    }
}
