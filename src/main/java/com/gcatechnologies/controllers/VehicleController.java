package com.gcatechnologies.controllers;

import com.gcatechnologies.constants.VehicleApiConstants;
import com.gcatechnologies.dto.VehicleDto;
import com.gcatechnologies.services.contracts.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = VehicleApiConstants.VEHICLE_API_PREFIX)
@RequiredArgsConstructor
public class VehicleController {

    private final IVehicleService iVehicleService;

    @GetMapping(VehicleApiConstants.LIST)
    private ResponseEntity<List<VehicleDto>> getAll() {
        List<VehicleDto> vehicleDtoList = iVehicleService.getAll();
        try {
            if(vehicleDtoList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(vehicleDtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(VehicleApiConstants.GET_BY_ID)
    private ResponseEntity<VehicleDto> getById(@RequestParam Long vehicleId) {
        return ResponseEntity.of(iVehicleService.getById(vehicleId));
    }

    @PostMapping(VehicleApiConstants.CREATE)
    private ResponseEntity<VehicleDto> save(@RequestBody VehicleDto vehicleDto) {
        return new ResponseEntity(iVehicleService.save(vehicleDto), HttpStatus.CREATED);
    }

    @DeleteMapping(VehicleApiConstants.DELETE)
    private ResponseEntity<Boolean> delete(@RequestParam Long vehicleDto) {
        return new ResponseEntity<Boolean>(iVehicleService.delete(vehicleDto) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}