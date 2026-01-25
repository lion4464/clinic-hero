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
    private Long visitId;  //TODO agar visitId yo'q bo'lsa u holda ukol sistema olgani kelgan bo'ladi,Lekin patientId berilishi SHART
    private Long patientId;
    private UUID createdByDoctorId;
    private OrderType orderType;
    private OrderStatus status;
    private LocalDateTime scheduledAt;
    private UUID performedById;
    private String resultShort;
    private String note;
}
