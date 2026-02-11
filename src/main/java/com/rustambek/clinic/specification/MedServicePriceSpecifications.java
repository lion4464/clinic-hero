package com.rustambek.clinic.specification;

import com.rustambek.clinic.price.med_service_type.MedServiceTypeEnum;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import org.springframework.data.jpa.domain.Specification;

public final class MedServicePriceSpecifications {
    private MedServicePriceSpecifications() {}

    public static Specification<MedServiceTypes> byFilter(String name, MedServiceTypeEnum type) {
        return Specification.where(nameContains(name)).and(typeEqual(type));
    }

    private static Specification<MedServiceTypes> typeEqual(MedServiceTypeEnum type) {
        return (root, query, cb) ->
                cb.equal(cb.lower(root.get("type")), type.name().toLowerCase());
    }

    public static Specification<MedServiceTypes> nameContains(String name) {
        if (name == null || name.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%");
    }
}

