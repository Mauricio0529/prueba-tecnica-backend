package com.gcatechnologies.repositories.mapper;

import com.gcatechnologies.dto.RentalDto;
import com.gcatechnologies.entities.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperRental {

    RentalDto toDto(Rental rentalEntity);

    @Mapping(target = "methodPaymentEntity", ignore = true)
    @Mapping(target = "users", ignore = true)
    Rental toEntity(RentalDto rentalDto);

    List<RentalDto> toDtoLis(List<Rental> rentalEntityList);

}