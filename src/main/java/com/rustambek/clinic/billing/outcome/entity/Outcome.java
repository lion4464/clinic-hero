package com.rustambek.clinic.billing.outcome.entity;

import com.rustambek.clinic.billing.outcome_type.entity.OutcomeType;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.generic.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "outcomes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Outcome extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "outcome_type_id")
    private Long outcomeTypeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "outcome_type_id", nullable = false, updatable = false,insertable = false)
    private OutcomeType outcomeType;

    @Column(name = "amount",nullable = false)
    private Long amount;
}
