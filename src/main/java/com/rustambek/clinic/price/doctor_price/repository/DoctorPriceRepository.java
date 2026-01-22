package com.rustambek.clinic.price.doctor_price.repository;

import com.rustambek.clinic.price.doctor_price.entity.DoctorPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DoctorPriceRepository extends JpaRepository<DoctorPrice, UUID>, JpaSpecificationExecutor<DoctorPrice> {
}
