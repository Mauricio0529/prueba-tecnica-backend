package com.gcatechnologies.repositories.mapper;

import com.gcatechnologies.dto.VehicleDto;
import com.gcatechnologies.entities.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperVehicle {

    VehicleDto toDto(Vehicle vehicleEntity);

    Vehicle toEntity(VehicleDto vehicleDto);

    List<VehicleDto> toDtoList(List<Vehicle> vehicleList);
}