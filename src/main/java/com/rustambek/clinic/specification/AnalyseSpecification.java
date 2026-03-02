package com.rustambek.clinic.specification;

import com.rustambek.clinic.analyse.entity.Analyse;
import org.springframework.data.jpa.domain.Specification;

public final class AnalyseSpecification {
    private AnalyseSpecification() {
    }

    public static Specification<Analyse> byFilter(String name, Long price) {
        return Specification.where(nameContains(name))
                .and(priceContains(price));
    }

    public static Specification<Analyse> nameContains(String name) {
        if (name == null || name.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase().trim() + "%");
    }

    public static Specification<Analyse> priceContains(Long price) {
        if (price == null || price == 0) return null;
        return (root, query, cb) ->
                cb.equal(root.get("price"), price);
    }

}
