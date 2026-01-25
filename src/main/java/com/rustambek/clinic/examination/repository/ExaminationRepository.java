package com.rustambek.clinic.examination.repository;

import com.rustambek.clinic.examination.entity.Examination;
import com.rustambek.clinic.examination.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long>, JpaSpecificationExecutor<Examination> {
    List<Examination> findAllByVisitIdAndStatus(Long visitId, Status status);
}
