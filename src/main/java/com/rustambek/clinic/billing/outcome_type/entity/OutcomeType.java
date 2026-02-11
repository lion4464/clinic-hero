package com.rustambek.clinic.billing.outcome_type.entity;

import com.rustambek.clinic.billing.invoice_item.model.ItemType;
import com.rustambek.clinic.generic.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "outcome_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutcomeType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
