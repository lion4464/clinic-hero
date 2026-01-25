package com.rustambek.clinic.price.med_service_type.repository;

import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedServiceTypeRepository
        extends JpaRepository<MedServiceTypes, Long>, JpaSpecificationExecutor<MedServiceTypes> {
}

