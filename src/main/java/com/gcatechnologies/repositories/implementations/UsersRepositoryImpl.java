package com.gcatechnologies.repositories.implementations;

import com.gcatechnologies.dto.UsersDto;
import com.gcatechnologies.entities.Users;
import com.gcatechnologies.repositories.contracts.IUsersRepository;
import com.gcatechnologies.repositories.mapper.MapperUsers;
import com.gcatechnologies.repositoriesCrudJpa.UsersRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements IUsersRepository {

    private final UsersRepositoryCrudJpa usersRepositoryCrudJpa;
    private final MapperUsers mapperUsers;

    @Override
    public List<UsersDto> getAll() {
        List<Users> usersEntityList = usersRepositoryCrudJpa.findAll();

        return mapperUsers.toUserListDto(usersEntityList);
    }

    @Override
    public Optional<UsersDto> getById(Long userId) {
        return usersRepositoryCrudJpa.findById(userId).map(mapperUsers::toUserDto);
    }

    @Override
    public Optional<UsersDto> getByUserName(String userName) {
        return usersRepositoryCrudJpa.findByUsername(userName).map(mapperUsers::toUserDto);
    }

    @Override
    public UsersDto save(UsersDto usersDto) {
        Users usersEntity = mapperUsers.toUserEntity(usersDto);

        usersEntity.setDateRegistration(LocalDateTime.now());

        usersEntity.getMethodPaymentList().stream().forEach(methodPayment -> methodPayment.setUsers(usersEntity));

        usersRepositoryCrudJpa.save(usersEntity);

        return mapperUsers.toUserDto(usersEntity);
    }

    @Override
    public void delete(Long userId) {
        usersRepositoryCrudJpa.deleteById(userId);
    }
}