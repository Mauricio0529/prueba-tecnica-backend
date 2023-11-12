package com.gcatechnologies.controllers;

import com.gcatechnologies.constants.UsersApiConstants;
import com.gcatechnologies.dto.UsersDto;
import com.gcatechnologies.exceptions.ErrorResponse;
import com.gcatechnologies.exceptions.NumberCardExistException;
import com.gcatechnologies.exceptions.UserNameAlreadyExistsException;
import com.gcatechnologies.services.contracts.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping(path = UsersApiConstants.USERS_API_PREFIX)
@RequiredArgsConstructor
public class UsersController {

    private final IUsersService iUsersService;

    @GetMapping(UsersApiConstants.LIST)
    private ResponseEntity<List<UsersDto>> getAll() {
        List<UsersDto> usersDtoList = iUsersService.getAll();
        try {
            if(usersDtoList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(usersDtoList);
        } catch (ResponseStatusException e) {
            log.error("Error en la solicitud", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(UsersApiConstants.GET_BY_ID)
    private ResponseEntity<UsersDto> getById(@RequestParam Long usersId) {
        return ResponseEntity.of(iUsersService.getById(usersId));
    }

    @GetMapping(UsersApiConstants.GET_BY_USERNAME)
    private ResponseEntity<UsersDto> getByUserName(@RequestParam String userName) {
        return ResponseEntity.of(iUsersService.getByUserName(userName));
    }

    @PostMapping(UsersApiConstants.CREATE)
    private ResponseEntity<?> save(@RequestBody UsersDto usersDto) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(iUsersService.save(usersDto));

        } catch (UserNameAlreadyExistsException userNameAlreadyExistsException) {
            String errorMessage = "El nombre de usuario ingresado ya existe";
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            log.error(errorMessage, userNameAlreadyExistsException);
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

    @PutMapping(UsersApiConstants.UPDATE)
    private ResponseEntity<UsersDto> update(@RequestBody UsersDto usersDto) {
        return ResponseEntity.of(iUsersService.update(usersDto));
    }

    @DeleteMapping(UsersApiConstants.DELETE)
    private ResponseEntity<Boolean> delete(@RequestParam Long usersId) {
        return new ResponseEntity<Boolean>(iUsersService.delete(usersId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}