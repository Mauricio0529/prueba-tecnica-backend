package com.gcatechnologies.repositories.contracts;

import com.gcatechnologies.dto.VehicleDto;

import java.util.List;
import java.util.Optional;

public interface IVehicleRepository {

    List<VehicleDto> getAll();

    Optional<VehicleDto> getById(Long vehicleId);

    VehicleDto save(VehicleDto vehicleDto);

    void delete(Long vehicleId);
}