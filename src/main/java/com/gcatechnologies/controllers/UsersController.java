package com.gcatechnologies.controllers;

import com.gcatechnologies.constants.UsersApiConstants;
import com.gcatechnologies.dto.UsersDto;
import com.gcatechnologies.services.contracts.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(usersDtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().build();
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
    private ResponseEntity<UsersDto> save(@RequestBody UsersDto usersDto) {
        return new ResponseEntity(iUsersService.save(usersDto), HttpStatus.CREATED);
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