package com.rustambek.clinic.billing.invoice.entity;

import com.rustambek.clinic.billing.invoice.model.InvoiceStatus;
import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false, insertable = false, updatable = false)
    private Visit visit;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false, updatable = false,insertable = false)
    private Patient patient;

    @Column(name = "patient_id")
    private Long patientId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private InvoiceStatus status = InvoiceStatus.OPEN;

    @Column(name = "total_amount")
    private Long totalAmount;


    @Column(name = "discount")
    private Integer discount;



}
