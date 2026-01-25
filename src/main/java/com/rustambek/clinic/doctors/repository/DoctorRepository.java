package com.rustambek.clinic.doctors.repository;

import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.doctors.projections.DoctorWithNowPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID>, JpaSpecificationExecutor<Doctor> {
    @Query(value = """
    SELECT 
            d.id as doctorId,
            d.full_name as doctorName,
            d.speciality as doctorSpeciality,
            dp.price as price
    FROM doctors d
    LEFT JOIN LATERAL (
        SELECT dp.*
        FROM doctor_price dp
        WHERE dp.doctor_id = d.id
        ORDER BY dp.created_at DESC
        LIMIT 1
    ) dp ON true
    WHERE d.id = ANY(:doctorIds)
    """, nativeQuery = true)
    List<DoctorWithNowPrice> findDoctorsWithLatestPrice(@Param("doctorIds") UUID[] doctorIds);

}

