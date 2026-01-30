package com.rustambek.clinic.pharmacy_products.dto;
import com.rustambek.clinic.pharmacy_products.model.UnitType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacyProductReq {
    private String name;
    private UnitType unit;
    private Integer quantity;
    private Long incomePrice;
    private Long salePrice;
    private LocalDateTime dueToDateTime;
}
