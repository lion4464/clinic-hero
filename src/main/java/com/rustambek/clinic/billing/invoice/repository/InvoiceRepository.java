package com.rustambek.clinic.billing.invoice.repository;

import com.rustambek.clinic.billing.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> , JpaSpecificationExecutor<Invoice> {
     Optional<Invoice> findByVisitId(Long visitId);
    @Query("""
        select coalesce(sum(i.totalAmount), 0)
        from Invoice i
        where i.isDeleted = false
          and i.createdAt >= :from
          and i.createdAt <= :to
          and i.status = 'PAID'
    """)
    Long sumTotalAmountFrom(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
