package com.rustambek.clinic.billing.invoice_item.dto;

import com.rustambek.clinic.billing.invoice_item.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {
    private Long id;
    private ItemType type;
    private Long unitPrice;
    private Integer quantity;
    private Long unitTotalAmount;
    private String name;
}
