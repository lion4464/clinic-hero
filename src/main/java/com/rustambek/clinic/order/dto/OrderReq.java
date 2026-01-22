package com.rustambek.clinic.order.dto;
import com.rustambek.clinic.order.model.OrderStatus;
import com.rustambek.clinic.order.model.OrderType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReq {
    private Long visitId;
    private UUID createdByDoctorId;
    private OrderType orderType;
    private OrderStatus status;
    private LocalDateTime scheduledAt;
    private UUID performedById;
    private String resultShort;
    private String note;
}
