package com.rustambek.clinic.specification;

import com.rustambek.clinic.patient.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class PatientSpecifications {

    public static Specification<Patient> byFilter(String fullName, String city, String region,String fromDate, String toDate) {
        return Specification.where(fullNameContains(fullName))
                .and(cityContains(city))
                .and(regionContains(region))
                .and(createdAtBetweenDates(fromDate,toDate));
    }
    public static Specification<Patient> createdAtBetweenDates(String fromDate, String toDate) {
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
