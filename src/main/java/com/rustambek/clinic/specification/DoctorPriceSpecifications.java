package com.rustambek.clinic.specification;

import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.price.doctor_price.entity.DoctorPrice;
import com.rustambek.clinic.visit.enums.VisitType;
import org.springframework.data.jpa.domain.Specification;

public final class DoctorPriceSpecifications {

    private DoctorPriceSpecifications() {
    }

    public static Specification<DoctorPrice> byFilter(VisitType visitType, Long price, ActiveType type) {
        return Specification.where(visitTypeEquals(visitType))
                .and(priceContains(price))
                .and(typeEquals(type));
    }

    public static Specification<DoctorPrice> visitTypeEquals(VisitType visitType) {
        if (visitType == null) return null;
        return (root, query, cb) -> cb.equal(root.get("visitType"), visitType);
    }

    public static Specification<DoctorPrice> priceContains(Long price) {
        if (price == null || price == 0) return null;
        return (root, query, cb) ->
                cb.equal(root.get("price"), price);
    }

    public static Specification<DoctorPrice> typeEquals(ActiveType type) {
        if (type == null) return null;
        return (root, query, cb) -> cb.equal(root.get("type"), type);
    }
}
