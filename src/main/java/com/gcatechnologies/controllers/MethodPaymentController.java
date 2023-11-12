package com.gcatechnologies.controllers;

import com.gcatechnologies.constants.MethodPaymentApiConstants;
import com.gcatechnologies.dto.MethodPaymentDto;
import com.gcatechnologies.exceptions.ErrorResponse;
import com.gcatechnologies.exceptions.NumberCardExistException;
import com.gcatechnologies.exceptions.ValidatedNumberCard;
import com.gcatechnologies.services.contracts.IMethodPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping(path = MethodPaymentApiConstants.METHOD_PAYMENT_API_PREFIX)
@RequiredArgsConstructor
public class MethodPaymentController {

    private final IMethodPaymentService iMethodPaymentService;

    @GetMapping(MethodPaymentApiConstants.LIST)
    private ResponseEntity<List<MethodPaymentDto>> getAll() {
        List<MethodPaymentDto> methodPaymentDtoList = iMethodPaymentService.getAll();
        try {
            if(methodPaymentDtoList == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(methodPaymentDtoList);
        } catch (ResponseStatusException e) {
            log.error("Error en la solicitud", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(MethodPaymentApiConstants.GET_BY_ID)
    private ResponseEntity<MethodPaymentDto> getById(@RequestParam Long methodPaymentId) {
        return ResponseEntity.of(iMethodPaymentService.getById(methodPaymentId));
    }

    @GetMapping(MethodPaymentApiConstants.GET_BY_USERID)
    private ResponseEntity<List<MethodPaymentDto>> getByUsers(@RequestParam Long userId) {
        return ResponseEntity.ok(iMethodPaymentService.getByUserId(userId));
    }

    @PostMapping(MethodPaymentApiConstants.CREATE)
    private ResponseEntity<?> save(@Valid @RequestBody MethodPaymentDto methodPaymentDto) {
        try {
            return new ResponseEntity(iMethodPaymentService.save(methodPaymentDto), HttpStatus.CREATED);

        } catch (ValidatedNumberCard validatedNumberCard) {
            String errorMessage = "Por favor ingrese el numero de la tarjeta";
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            log.error(errorMessage, validatedNumberCard);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (NumberCardExistException numberCardExistException) {
            String errorMessage = "El numero de la tarjeta ya se encuentra registrada";
            log.error(errorMessage, numberCardExistException);
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (ResponseStatusException e) {
            log.error("Error en la solicitud", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(MethodPaymentApiConstants.UPDATE_BY_USER)
    private ResponseEntity<MethodPaymentDto> updateByUsers(@RequestBody MethodPaymentDto methodPaymentDto) {
        return ResponseEntity.of(iMethodPaymentService.updateByUsers(methodPaymentDto));
    }

    @DeleteMapping(MethodPaymentApiConstants.DELETE_BY_USER)
    private ResponseEntity<Boolean> deleteByUsers(@RequestParam Long methodPaymentId, @RequestParam Long userId) {
        return new ResponseEntity<Boolean>(iMethodPaymentService.deleteByUser(methodPaymentId, userId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}