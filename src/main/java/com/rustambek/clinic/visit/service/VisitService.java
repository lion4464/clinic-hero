package com.rustambek.clinic.visit.service;

import com.rustambek.clinic.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
}
