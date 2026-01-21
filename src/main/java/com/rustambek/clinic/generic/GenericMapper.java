package com.rustambek.clinic.generic;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface GenericMapper<E, D, R> {
    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);

    E toEntity(R request);

    default Page<D> toDtoPage(Page<E> entityPage) {
        List<D> dtos = toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalElements());
    }


    void setModel(@MappingTarget E model, R request);
}
