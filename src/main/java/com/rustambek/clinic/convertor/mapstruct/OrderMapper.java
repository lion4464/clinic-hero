package com.rustambek.clinic.convertor.mapstruct;

import com.rustambek.clinic.generic.GenericMapper;
import com.rustambek.clinic.order.dto.OrderDto;
import com.rustambek.clinic.order.dto.OrderReq;
import com.rustambek.clinic.order.entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {DoctorMapper.class,VisitMapper.class,}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends GenericMapper<Order, OrderDto, OrderReq> {
}
