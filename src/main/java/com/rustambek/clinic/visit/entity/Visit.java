package com.rustambek.clinic.visit.entity;

import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.patient.entity.Patient;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.enums.VisitType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false, updatable = false,insertable = false)
    private Patient patient;

    @Column(name = "visit_date_time", nullable = false)
    private LocalDateTime visitDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "visit_type", nullable = false, length = 30)
    private VisitType visitType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private VisitStatus status;

    @Column(name = "primary_doctor_id")
    private UUID primaryDoctorId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "primary_doctor_id", nullable = false, updatable = false,insertable = false)
    private Doctor primaryDoctor;


    @Column(name = "note", length = 500)
    private String note;
}
