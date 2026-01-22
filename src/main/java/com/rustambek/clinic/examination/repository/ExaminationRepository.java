package com.rustambek.clinic.examination.repository;

import com.rustambek.clinic.examination.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long>, JpaSpecificationExecutor<Examination> {
}
