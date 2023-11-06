package com.gcatechnologies.services.contracts;

import com.gcatechnologies.dto.UsersDto;

import java.util.List;
import java.util.Optional;

public interface IUsersService {

    List<UsersDto> getAll();

    Optional<UsersDto> getById(Long usersId);

    Optional<UsersDto> getByUserName(String userName);

    UsersDto save(UsersDto usersDto);

    Optional<UsersDto> update(UsersDto usersDto);

    Boolean delete(Long userId);

}