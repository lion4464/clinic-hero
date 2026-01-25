package com.rustambek.clinic.billing.invoice_item.repository;

import com.rustambek.clinic.billing.invoice_item.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
