package com.rustambek.clinic.doctors.projections;

import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.price.doctor_price.entity.DoctorPrice;

import java.util.UUID;

public interface DoctorWithNowPrice {
    UUID getDoctorId();
    String getDoctorName();
    String getDoctorSpeciality();
    Long getPrice();
}
