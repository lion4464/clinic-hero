package com.rustambek.clinic.billing.invoice.repository;

import com.rustambek.clinic.billing.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
     Optional<Invoice> findByVisitId(Long visitId);
}
