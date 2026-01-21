package com.rustambek.clinic.specification;

import com.rustambek.clinic.patient.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

public final class PatientSpecifications {

    public static Specification<Patient> byFilter(String fullName, String city, String region) {
        return Specification.where(fullNameContains(fullName))
                .and(cityContains(city))
                .and(regionContains(region));
    }

    public static Specification<Patient> fullNameContains(String fullName) {
        if (fullName == null || fullName.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase().trim() + "%");
    }

    public static Specification<Patient> cityContains(String city) {
        if (city == null || city.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase().trim() + "%");
    }

    public static Specification<Patient> regionContains(String region) {
        if (region == null || region.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("region")), "%" + region.toLowerCase().trim() + "%");
    }
}
