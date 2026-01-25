package com.rustambek.clinic.specification;

import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import org.springframework.data.jpa.domain.Specification;

public final class MedServicePriceSpecifications {
    private MedServicePriceSpecifications() {}

    public static Specification<MedServiceTypes> byFilter(String name) {
        return Specification.where(nameContains(name));
    }

    public static Specification<MedServiceTypes> nameContains(String name) {
        if (name == null || name.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%");
    }
}

