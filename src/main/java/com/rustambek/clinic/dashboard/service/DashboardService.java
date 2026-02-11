package com.rustambek.clinic.dashboard.service;

import com.rustambek.clinic.billing.invoice.service.InvoiceService;
import com.rustambek.clinic.dashboard.dto.DashboardDto;
import com.rustambek.clinic.doctors.service.DoctorService;
import com.rustambek.clinic.patient.service.PatientService;
import com.rustambek.clinic.pharmacy_products.service.PharmacyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final InvoiceService invoiceService;
    private final PharmacyProductService pharmacyProductService;

    public DashboardDto getWidget(String fromDate, String toDate) {
        LocalDateTime from = convertLocalDateTime(fromDate);
        LocalDateTime to = convertLocalDateTime(toDate).plusDays(1);

        Long doctors = doctorService.countByDeleteFalse(from,to);
        Long patients = patientService.countByDeleteFalse(from,to);
        Long pharmacyProducts = pharmacyProductService.countByDeleteFalse(from,to);
        Long totalIncome = invoiceService.totalSumInvoiceAmountLatestYear(from,to);

        return DashboardDto.builder().totalDoctors(doctors).totalPatients(patients).totalPharmacyProducts(pharmacyProducts).totalIncomes(totalIncome).build();
    }

    private LocalDateTime convertLocalDateTime(String fromDate) {
        if (fromDate == null || fromDate.isBlank() ) return null;

        LocalDate from = LocalDate.parse(fromDate.trim());
        LocalDateTime start = from.atStartOfDay();
        return start;
    }
}
