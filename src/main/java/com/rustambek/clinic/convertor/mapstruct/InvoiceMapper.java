package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.billing.invoice.dto.InvoiceDto;
import com.rustambek.clinic.billing.invoice.entity.Invoice;
import com.rustambek.clinic.billing.invoice_item.dto.InvoiceItemDto;
import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {InvoiceItemMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper extends GenericAuditMapper<Invoice, InvoiceDto> {
}
