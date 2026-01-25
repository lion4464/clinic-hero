package com.rustambek.clinic.specification;

import com.rustambek.clinic.pharmacy_products.entity.PharmacyProduct;
import com.rustambek.clinic.pharmacy_products.model.UnitType;
import org.springframework.data.jpa.domain.Specification;

public final class PharmacyProductSpecifications {
    private PharmacyProductSpecifications() {}

    public static Specification<PharmacyProduct> byFilter(String name, UnitType unit) {
        return Specification.where(nameContains(name))
                .and(unitEq(unit));
    }

    public static Specification<PharmacyProduct> nameContains(String name) {
        if (name == null || name.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%");
    }

    public static Specification<PharmacyProduct> unitEq(UnitType unit) {
        if (unit == null) return null;
        return (root, query, cb) -> cb.equal(root.get("unit"), unit);
    }
}
