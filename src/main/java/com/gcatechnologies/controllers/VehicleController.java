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

import static org.hibernate.query.sqm.tree.SqmNode.log;

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
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(vehicleDtoList);
        } catch (ResponseStatusException e) {
            log.error("Error en la solicitud", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(VehicleApiConstants.GET_BY_ID)
    private ResponseEntity<VehicleDto> getById(@RequestParam Long vehicleId) {
        return ResponseEntity.of(iVehicleService.getById(vehicleId));
    }

    @PostMapping(VehicleApiConstants.CREATE)
    private ResponseEntity<VehicleDto> save(@RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iVehicleService.save(vehicleDto));
    }

    @DeleteMapping(VehicleApiConstants.DELETE)
    private ResponseEntity<Boolean> delete(@RequestParam Long vehicleDto) {
        return new ResponseEntity<Boolean>(iVehicleService.delete(vehicleDto) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}