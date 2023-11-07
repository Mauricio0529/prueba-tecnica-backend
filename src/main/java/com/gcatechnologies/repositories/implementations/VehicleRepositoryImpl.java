package com.gcatechnologies.repositories.implementations;

import com.gcatechnologies.dto.VehicleDto;
import com.gcatechnologies.entities.Vehicle;
import com.gcatechnologies.repositories.contracts.IVehicleRepository;
import com.gcatechnologies.repositories.mapper.MapperVehicle;
import com.gcatechnologies.repositoriesCrudJpa.VehicleRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements IVehicleRepository {

    private final VehicleRepositoryCrudJpa vehicleRepositoryCrudJpa;
    private final MapperVehicle mapperVehicle;

    @Override
    public List<VehicleDto> getAll() {
        return mapperVehicle.toDtoList(vehicleRepositoryCrudJpa.findAll());
    }

    @Override
    public Optional<VehicleDto> getById(Long vehicleId) {
        return vehicleRepositoryCrudJpa.findById(vehicleId).map(mapperVehicle::toDto);
    }

    @Override
    public VehicleDto save(VehicleDto vehicleDto) {
        Vehicle vehicleEntity = vehicleRepositoryCrudJpa.save(mapperVehicle.toEntity(vehicleDto));
        return mapperVehicle.toDto(vehicleEntity);
    }

    @Override
    public void delete(Long vehicleId) {
        vehicleRepositoryCrudJpa.deleteById(vehicleId);
    }
}