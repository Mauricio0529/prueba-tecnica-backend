package com.gcatechnologies.repositories.implementations;

import com.gcatechnologies.dto.MethodPaymentDto;
import com.gcatechnologies.entities.MethodPayment;
import com.gcatechnologies.repositories.contracts.IMethodPaymentRepository;
import com.gcatechnologies.repositories.mapper.MapperMethodPayment;
import com.gcatechnologies.repositoriesCrudJpa.MethodPaymentRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MethodPaymentRepositoryImpl implements IMethodPaymentRepository {

    private final MethodPaymentRepositoryCrudJpa methodPaymentRepositoryCrudJpa;

    private final MapperMethodPayment mapperMethodPayment;

    @Override
    public List<MethodPaymentDto> getAll() {
        return mapperMethodPayment.toDtoList(methodPaymentRepositoryCrudJpa.findAll());
    }

    @Override
    public Optional<MethodPaymentDto> getById(Long methodPaymentId) {
        return methodPaymentRepositoryCrudJpa.findById(methodPaymentId).map(mapperMethodPayment::toDto);
    }

    @Override
    public List<MethodPaymentDto> getByUserId(Long userId) {
        return mapperMethodPayment.toDtoList(methodPaymentRepositoryCrudJpa.findByUsersId(userId));
    }

    @Override
    public MethodPaymentDto save(MethodPaymentDto methodPaymentDto) {
        MethodPayment methodPaymentEntity = methodPaymentRepositoryCrudJpa.save(mapperMethodPayment.toEntity(methodPaymentDto));
        return mapperMethodPayment.toDto(methodPaymentEntity);
    }

    @Override
    public void deleteByUserId(Long methodPaymentId) {
        methodPaymentRepositoryCrudJpa.deleteById(methodPaymentId);
    }
}