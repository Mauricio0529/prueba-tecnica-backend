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
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(rentalDtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().build();
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
            return new ResponseEntity(iRentalService.save(rentalDto), HttpStatus.CREATED);
        } catch (MethodPaymentToUserNotExistException methodPaymentToUserNotExistException) {

            String errorMessage = "El medio de pago no existe en la cuenta de usuario";
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(RentalApiConstants.UPDATE_STATUS)
    private ResponseEntity<RentalDto> updateStatus(@RequestParam Long rentalDto, @RequestParam String newStatus) {
        return ResponseEntity.of(iRentalService.updateStatus(rentalDto, newStatus));
    }
}