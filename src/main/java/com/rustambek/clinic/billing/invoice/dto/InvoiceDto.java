package com.rustambek.clinic.billing.invoice.dto;

import com.rustambek.clinic.billing.invoice.model.InvoiceStatus;
import com.rustambek.clinic.billing.invoice_item.dto.InvoiceItemDto;
import com.rustambek.clinic.examination.dto.ExaminationDto;
import com.rustambek.clinic.price.med_service_type.dto.MedServicePriceDto;
import com.rustambek.clinic.price.med_service_type.entity.MedServiceTypes;
import com.rustambek.clinic.visit.dto.VisitDto;
import com.rustambek.clinic.visit.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long id;
    private VisitDto visit;
    private InvoiceStatus status;
    private Long totalAmount;
    private Integer discount;
    private List<InvoiceItemDto> items;

}
