package com.gcatechnologies.services.implementations;

import com.gcatechnologies.dto.UsersDto;
import com.gcatechnologies.exceptions.NumberCardExistException;
import com.gcatechnologies.exceptions.UserNameAlreadyExistsException;
import com.gcatechnologies.repositories.contracts.IMethodPaymentRepository;
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
    private final IMethodPaymentRepository iMethodPaymentRepository;

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
        Optional<UsersDto> usersDtoOptional = iUsersRepository.getByUserName(userName);
        if(usersDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        return usersDtoOptional;
    }

    @Override
    public UsersDto save(UsersDto usersDto) {
        Optional<UsersDto> usersDtoOptional = getByUserName(usersDto.getUsername());
        if(usersDtoOptional.isPresent()) {
            throw new UserNameAlreadyExistsException();
        }
        /**
         * VALIDAR QUE EL NUMERO DE LA TARJETA NO ESTE DUPLICADO
         */
        usersDto.getMethodPaymentList().stream().forEach(methodPayment -> {
            Integer numberCardRepeated = iMethodPaymentRepository.countByNumberCard(methodPayment.getNumberCard());
            if(numberCardRepeated != 0) {
                throw new NumberCardExistException();
            }
        });
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