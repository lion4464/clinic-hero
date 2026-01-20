package com.rustambek.clinic.patient.entity;


import com.rustambek.clinic.generic.BaseEntity;
import com.rustambek.clinic.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "city", length = 120)
    private String city;

    @Column(name = "region", length = 120)
    private String region;

    @Column(name = "phone", length = 30)
    private String phone;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Visit> visits = new ArrayList<>();


}

