package com.gcatechnologies.repositories.contracts;

import com.gcatechnologies.dto.MethodPaymentDto;

import java.util.List;
import java.util.Optional;

public interface IMethodPaymentRepository {

    List<MethodPaymentDto> getAll();

    Optional<MethodPaymentDto> getById(Long userId);

    List<MethodPaymentDto> getByUserId(Long userId);

    MethodPaymentDto save(MethodPaymentDto methodPaymentDto);

    MethodPaymentDto saveNewMethodPaymentByUser(MethodPaymentDto methodPaymentDto);

    void deleteByUserId(Long methodPaymentId);
}