package com.rustambek.clinic.doctors.entity;

import com.rustambek.clinic.doctors.enums.ActiveType;
import com.rustambek.clinic.generic.BaseEntity;
import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, length = 80)
    private String speciality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ActiveType type;
}
