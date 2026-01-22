package com.rustambek.clinic.examination.entity;

import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.examination.model.Status;
import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.visit.entity.Visit;
import com.rustambek.clinic.visit.enums.VisitType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "examinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Examination extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false, updatable = false,insertable = false)
    private Visit visit;

    @Column(name = "visit_id")
    private Long visitId;

//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false, length = 30)
//    private Status status;


    @Column(name = "doctor_id")
    private UUID doctorId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false, updatable = false,insertable = false)
    private Doctor doctor;

    @Column(name = "complaint", length = 500)
    private String complaint;

    @Column(name = "diagnosis", length = 500)
    private String diagnosis;

    @Column(name = "comment", length = 500)
    private String comment;
}
