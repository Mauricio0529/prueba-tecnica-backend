package com.gcatechnologies.services.contracts;

import com.gcatechnologies.dto.MethodPaymentDto;

import java.util.List;
import java.util.Optional;

public interface IMethodPaymentService {

    List<MethodPaymentDto> getAll();

    Optional<MethodPaymentDto> getById(Long methodPaymentId);

    List<MethodPaymentDto> getByUserId(Long userId);

    MethodPaymentDto save(MethodPaymentDto methodPaymentDto);

    Optional<MethodPaymentDto> updateByUsers(MethodPaymentDto methodPaymentDto);

    Boolean deleteByUser(Long methodPaymentId, Long userId);
}