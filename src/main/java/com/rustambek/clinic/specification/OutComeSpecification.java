package com.rustambek.clinic.specification;

import com.rustambek.clinic.billing.outcome.entity.Outcome;
import com.rustambek.clinic.patient.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OutComeSpecification {

    public static Specification<Outcome> byFilter(Long outcomeTypeId, String createdAtFrom, String createdAtToDate) {
        return Specification.where(equalToOutcomeTypeId(outcomeTypeId))
                .and(createdAtBetweenDates(createdAtFrom,createdAtToDate));
    }

    private static Specification<Outcome> equalToOutcomeTypeId(Long outcomeTypeId) {
        if (outcomeTypeId == null) return null;
        return (root, query, cb) -> cb.equal(root.get("outcomeTypeId"), outcomeTypeId);

    }

    public static Specification<Outcome> createdAtBetweenDates(String fromDate, String toDate) {
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
}
