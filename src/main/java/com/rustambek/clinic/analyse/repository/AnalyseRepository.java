package com.rustambek.clinic.analyse.repository;

import com.rustambek.clinic.analyse.entity.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AnalyseRepository extends JpaRepository<Analyse, UUID>, JpaSpecificationExecutor<Analyse> {
}
