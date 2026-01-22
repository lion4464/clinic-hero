package com.rustambek.clinic.order.dto;

import com.rustambek.clinic.doctors.dto.DoctorDto;
import com.rustambek.clinic.doctors.entity.Doctor;
import com.rustambek.clinic.order.model.OrderStatus;
import com.rustambek.clinic.order.model.OrderType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long visitId;
    private UUID createdByDoctorId;
    private DoctorDto createdByDoctor;
    private OrderType orderType;
    private OrderStatus status;
    private LocalDateTime scheduledAt;
    private UUID performedById;
    private DoctorDto performedBy;
    private String resultShort;
    private String note;
}
