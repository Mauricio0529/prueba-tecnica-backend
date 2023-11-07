package com.gcatechnologies.services.implementations;

import com.gcatechnologies.dto.UsersDto;
import com.gcatechnologies.exceptions.UserNameAlreadyExistsException;
import com.gcatechnologies.repositories.contracts.IUsersRepository;
import com.gcatechnologies.services.contracts.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements IUsersService {

    private final IUsersRepository iUsersRepository;

    @Override
    public List<UsersDto> getAll() {
        return iUsersRepository.getAll();
    }

    @Override
    public Optional<UsersDto> getById(Long usersId) {
        Optional<UsersDto> usersDtoOptional = iUsersRepository.getById(usersId);
        if(usersDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        return usersDtoOptional;
    }

    @Override
    public Optional<UsersDto> getByUserName(String userName) {
        return iUsersRepository.getByUserName(userName);
    }

    @Override
    public UsersDto save(UsersDto usersDto) {
        Optional<UsersDto> usersDtoOptional = getByUserName(usersDto.getUsername());
        if(usersDtoOptional.isPresent()) {
            throw new UserNameAlreadyExistsException();
        }
        return iUsersRepository.save(usersDto);
    }

    @Override
    public Optional<UsersDto> update(UsersDto usersDto) {
        Optional<UsersDto> usersDtoOptional = getById(usersDto.getId());
        if(usersDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        iUsersRepository.save(usersDto);
        return Optional.of(usersDto);
    }

    @Override
    public Boolean delete(Long userId) {
        if(getById(userId).isEmpty()) {
            return false;
        }
        iUsersRepository.delete(userId);
        return true;
    }
}