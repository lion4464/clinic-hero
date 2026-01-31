package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.billing.invoice_item.dto.InvoiceItemDto;
import com.rustambek.clinic.billing.invoice_item.entity.InvoiceItem;
import com.rustambek.clinic.generic.GenericAuditMapper;
import com.rustambek.clinic.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceItemMapper extends GenericAuditMapper<InvoiceItem, InvoiceItemDto> {
}
