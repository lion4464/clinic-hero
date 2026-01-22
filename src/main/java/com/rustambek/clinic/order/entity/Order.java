package com.rustambek.clinic.order.entity;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.order.model.OrderStatus;
import com.rustambek.clinic.order.model.OrderType;
import com.rustambek.clinic.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false, insertable = false, updatable = false)
    private Visit visit;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_doctor_id", nullable = false, insertable = false, updatable = false)
    private Doctor createdByDoctor;

    @Column(name = "created_by_doctor_id", nullable = false)
    private UUID createdByDoctorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false, length = 30)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "performed_by_id", nullable = false, insertable = false, updatable = false)
    private Doctor performedBy;

    @Column(name = "performed_by_id")
    private UUID performedById;

    @Column(name = "result_short", length = 500)
    private String resultShort;

    @Column(name = "note", length = 500)
    private String note;

}
