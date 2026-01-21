package com.rustambek.clinic.specification;


import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.doctors.enums.ActiveType;
import org.springframework.data.jpa.domain.Specification;

public final class DoctorSpecifications {

    private DoctorSpecifications() {}


    public static Specification<Doctor> byFilter(String fullName, String speciality, ActiveType type) {
        return Specification.where(fullNameContains(fullName))
                .and(specialityContains(speciality))
                .and(typeEquals(type));
    }

    public static Specification<Doctor> fullNameContains(String fullName) {
        if (fullName == null || fullName.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase().trim() + "%");
    }

    public static Specification<Doctor> specialityContains(String speciality) {
        if (speciality == null || speciality.isBlank()) return null;
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("speciality")), "%" + speciality.toLowerCase().trim() + "%");
    }

    public static Specification<Doctor> typeEquals(ActiveType type) {
        if (type == null) return null;
        return (root, query, cb) -> cb.equal(root.get("type"), type);
    }
}

