package com.rustambek.clinic.analyse.entity;

import com.rustambek.clinic.generic.BaseEntity;
import jakarta.persistence.*;

public class Analyse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private Long price;
}
