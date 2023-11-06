package com.gcatechnologies.services.contracts;

import com.gcatechnologies.dto.RentalDto;

import java.util.List;
import java.util.Optional;

public interface IRentalService {

    List<RentalDto> getAll();

    Optional<RentalDto> getById(Long rentalId);

    Optional<RentalDto> getByUserId(Long userId);

    RentalDto save(RentalDto rentalDto);

    Optional<RentalDto> updateStatus(Long rentalId, String newStatus);
}