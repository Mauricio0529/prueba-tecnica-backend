package com.gcatechnologies.repositories.mapper;

import com.gcatechnologies.dto.MethodPaymentDto;
import com.gcatechnologies.entities.MethodPayment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperMethodPayment {

    MethodPaymentDto toDto(MethodPayment methodPaymentEntity);

    MethodPayment toEntity(MethodPaymentDto methodPaymentDto);

    List<MethodPaymentDto> toDtoList(List<MethodPayment> methodPaymentEntityList);
}