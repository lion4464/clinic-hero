package com.rustambek.clinic.price.doctor_price.entity;

import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.visit.enums.VisitType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctor_price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorPrice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "visit_type", nullable = false, length = 30)
    private VisitType visitType;

    @Column(name = "price",nullable = false)
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ActiveType type;

}
