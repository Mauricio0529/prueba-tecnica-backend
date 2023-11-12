package com.gcatechnologies.controllers;

import com.gcatechnologies.constants.RentalApiConstants;
import com.gcatechnologies.dto.RentalDto;
import com.gcatechnologies.exceptions.ErrorResponse;
import com.gcatechnologies.exceptions.MethodPaymentToUserNotExistException;
import com.gcatechnologies.services.contracts.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping(path = RentalApiConstants.RENTAL_API_PREFIX)
@RequiredArgsConstructor
public class RentalController {

    private final IRentalService iRentalService;

    @GetMapping(RentalApiConstants.LIST)
    private ResponseEntity<List<RentalDto>> getAll() {
        List<RentalDto> rentalDtoList = iRentalService.getAll();
        try {
            if(rentalDtoList == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(rentalDtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(RentalApiConstants.GET_BY_ID)
    private ResponseEntity<RentalDto> getById(@RequestParam Long rentalId) {
        return ResponseEntity.of(iRentalService.getById(rentalId));
    }

    @GetMapping(RentalApiConstants.GET_BY_USER_ID)
    private ResponseEntity<RentalDto> getByUserId(@RequestParam Long userId) {
        return ResponseEntity.of(iRentalService.getByUserId(userId));
    }

    @PostMapping(RentalApiConstants.CREATE)
    private ResponseEntity<?> save(@RequestBody RentalDto rentalDto) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iRentalService.save(rentalDto));
        } catch (MethodPaymentToUserNotExistException methodPaymentToUserNotExistException) {
            String errorMessage = "El medio de pago no existe en la cuenta de usuario";
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            log.error(errorResponse, methodPaymentToUserNotExistException);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (ResponseStatusException e) {
            log.error("Error en la solicitud", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(RentalApiConstants.UPDATE_STATUS)
    private ResponseEntity<RentalDto> updateStatus(@RequestParam Long rentalId, @RequestParam String newStatus) {
        return ResponseEntity.of(iRentalService.updateStatus(rentalId, newStatus));
    }
}