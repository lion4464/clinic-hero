package com.rustambek.clinic.billing.invoice.service;

import com.rustambek.clinic.billing.invoice.dto.InvoiceDto;
import com.rustambek.clinic.billing.invoice.entity.Invoice;
import com.rustambek.clinic.billing.invoice.model.InvoiceStatus;
import com.rustambek.clinic.billing.invoice.repository.InvoiceRepository;
import com.rustambek.clinic.billing.invoice_item.entity.InvoiceItem;
import com.rustambek.clinic.billing.invoice_item.model.ItemType;
import com.rustambek.clinic.billing.invoice_item.repository.InvoiceItemRepository;
import com.rustambek.clinic.convertor.mapstruct.InvoiceMapper;
import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.projections.DoctorWithNowPrice;
import com.rustambek.clinic.doctors.service.DoctorService;
import com.rustambek.clinic.examination.entity.Examination;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.nurse_medication.entity.MedServicePriceNurseMedication;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductOutcome;
import com.rustambek.clinic.specification.InvoiceSpecification;
import com.rustambek.clinic.visit.entity.Visit;
import com.rustambek.clinic.visit.enums.VisitStatus;
import com.rustambek.clinic.visit.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final VisitService visitService;
    private final DoctorService doctorService;
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceMapper invoiceMapper;

    public Invoice createInvoiceWithInvoiceItemsForExaminations(Long visitId, List<Examination> exams ) {
        Visit visit = visitService.getModel(visitId);
        Invoice invoice = invoiceRepository.findByVisitId(visitId)
                .orElseGet(() -> invoiceRepository.save(Invoice.builder().visitId(visitId).patientId(visit.getPatientId()).status(InvoiceStatus.OPEN).build()));
         Map<UUID,Examination> examinationMap = exams.stream().collect(Collectors.toMap(Examination::getDoctorId, e -> e));
        Set<InvoiceItem> invoiceItems = new HashSet<>();
        // doctors return with latst prices
        List<DoctorWithNowPrice> doctorsWithLatestPrice = doctorService.findDoctorsWithLatestPrice(examinationMap.keySet());
        Long totalAmount = 0l;
        for (DoctorWithNowPrice doctorWithNowPrice : doctorsWithLatestPrice) {
            if (doctorWithNowPrice.getPrice()==null)
                throw new DataNotFoundException("Doctor with non-existent price name="+doctorWithNowPrice.getDoctorName()+",speciality="+doctorWithNowPrice.getDoctorSpeciality());
            InvoiceItem model = InvoiceItem.builder().name(doctorWithNowPrice.getDoctorName()+"("+doctorWithNowPrice.getDoctorSpeciality()+")").invoiceId(invoice.getId()).type(ItemType.EXAMINATION).refId(examinationMap.get(doctorWithNowPrice.getDoctorId()).getId()).unitPrice(doctorWithNowPrice.getPrice()).unitTotalAmount(doctorWithNowPrice.getPrice()).quantity(1).build();
            totalAmount += doctorWithNowPrice.getPrice();
            invoiceItems.add(model);
        }
        invoiceItemRepository.saveAll(invoiceItems);
        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }

    public Invoice createInvoiceWithInvoiceItemsForNurseCares(Long visitId, List<MedServicePriceNurseMedication> medServicePriceNurseMedications) {
        Visit visit = visitService.getModel(visitId);
        Invoice invoice = invoiceRepository.findByVisitId(visitId)
                .orElseGet(() -> invoiceRepository.save(Invoice.builder().status(InvoiceStatus.OPEN).visitId(visitId).patientId(visit.getPatientId()).build()));

        Set<InvoiceItem> invoiceItems = new HashSet<>();
         Long totalAmount = 0l;
        for (MedServicePriceNurseMedication  medServiceWithLatestPrice : medServicePriceNurseMedications) {
            if (medServiceWithLatestPrice.getMedServiceTypes()==null)
                throw new DataNotFoundException("Med service price not found name="+medServiceWithLatestPrice.getMedServiceTypes().getName());

            InvoiceItem model = InvoiceItem.builder().name(medServiceWithLatestPrice.getMedServiceTypes().getName()).invoiceId(invoice.getId()).type(ItemType.SERVICE).refId(medServiceWithLatestPrice.getId()).unitPrice(medServiceWithLatestPrice.getMedServiceTypes().getPrice()).unitTotalAmount(medServiceWithLatestPrice.getMedServiceTypes().getPrice()).quantity(medServiceWithLatestPrice.getQuantity()).build();
            totalAmount += medServiceWithLatestPrice.getMedServiceTypes().getPrice();
            invoiceItems.add(model);
        }
        invoiceItemRepository.saveAll(invoiceItems);
        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }
    public Invoice createInvoiceWithInvoiceItemsForProductOutcome(Long visitId, List<PharmacyProductOutcome> productOutcomes) {
        Visit visit = visitService.getModel(visitId);
        Invoice invoice = invoiceRepository.findByVisitId(visitId)
                .orElseGet(() -> invoiceRepository.save(Invoice.builder().status(InvoiceStatus.OPEN).visitId(visitId).patientId(visit.getPatientId()).build()));
        Set<InvoiceItem> invoiceItems = new HashSet<>();
        Long totalAmount = 0l;
        for (PharmacyProductOutcome  productOutcome : productOutcomes) {
            if (productOutcome.getSalePrice()==null)
                throw new DataNotFoundException("Product sale price not found, name="+productOutcome.getPharmacyProduct().getName());
            InvoiceItem model = InvoiceItem.builder().name(productOutcome.getPharmacyProduct().getName()).invoiceId(invoice.getId()).type(ItemType.PRODUCT).refId(productOutcome.getId()).unitPrice(productOutcome.getPharmacyProduct().getSalePrice()).unitTotalAmount(productOutcome.getPharmacyProduct().getSalePrice()*productOutcome.getQuantity()).quantity(productOutcome.getQuantity()).build();
            totalAmount += productOutcome.getSalePrice();
            invoiceItems.add(model);
        }
        invoiceItemRepository.saveAll(invoiceItems);
        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }

    public Page<InvoiceDto> pageable(Long visitId, InvoiceStatus status, Long patientId, Pageable pageable) {
        return invoiceMapper.toDtoPage(invoiceRepository.findAll(InvoiceSpecification.byFilter(visitId,patientId,status),pageable));
    }
    @Transactional
    public InvoiceDto markAspaid(Long id) {
      Invoice model = getModel(id);
        Visit visit = visitService.getModel(model.getVisitId());
        visit.setStatus(VisitStatus.CLOSED);
        model.setStatus(InvoiceStatus.PAID);
        visitService.markAsPaid(visit);
        invoiceRepository.save(model);
        return invoiceMapper.toDto(model);
    }

    private Invoice getModel(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() ->new DataNotFoundException("Invoice not found with id="+id));
    }
}
