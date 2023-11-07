package com.gcatechnologies.services.implementations;

import com.gcatechnologies.dto.VehicleDto;
import com.gcatechnologies.repositories.contracts.IVehicleRepository;
import com.gcatechnologies.services.contracts.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements IVehicleService {

    private final IVehicleRepository iVehicleRepository;

    @Override
    public List<VehicleDto> getAll() {
        return iVehicleRepository.getAll();
    }

    @Override
    public Optional<VehicleDto> getById(Long vehicleId) {
        Optional<VehicleDto> vehicleDtoOptional = iVehicleRepository.getById(vehicleId);

        if(vehicleDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        return vehicleDtoOptional;
    }

    @Override
    public VehicleDto save(VehicleDto vehicleDto) {
        return iVehicleRepository.save(vehicleDto);
    }

    @Override
    public Boolean delete(Long vehicleId) {
        if(getById(vehicleId).isEmpty()) {
            return false;
        }
        iVehicleRepository.delete(vehicleId);
        return true;
    }
}