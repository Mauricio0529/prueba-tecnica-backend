package com.gcatechnologies.services.contracts;

import com.gcatechnologies.dto.VehicleDto;

import java.util.List;
import java.util.Optional;

public interface IVehicleService {

    List<VehicleDto> getAll();

    Optional<VehicleDto> getById(Long vehicleId);

    VehicleDto save(VehicleDto vehicleDto);

    Boolean delete(Long vehicleId);
}