package com.rustambek.clinic.patient.service;

import com.rustambek.clinic.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
private final PatientRepository patientRepository;
}
