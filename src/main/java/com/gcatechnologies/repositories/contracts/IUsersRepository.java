package com.gcatechnologies.repositories.contracts;

import com.gcatechnologies.dto.UsersDto;

import java.util.List;
import java.util.Optional;

public interface IUsersRepository {

    List<UsersDto> getAll();

    Optional<UsersDto> getById(Long userId);

    Optional<UsersDto> getByUserName(String userName);

    UsersDto save(UsersDto users);

    void delete(Long userId);
}