package com.gcatechnologies.repositories.mapper;

import com.gcatechnologies.dto.UsersDto;
import com.gcatechnologies.entities.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperUsers {

    UsersDto toUserDto(Users usersEntity);

    Users toUserEntity(UsersDto usersDto);

    List<UsersDto> toUserListDto(List<Users> usersList);
}