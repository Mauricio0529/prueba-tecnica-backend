package com.gcatechnologies.repositories.contracts;

import com.gcatechnologies.dto.RentalDto;

import java.util.List;
import java.util.Optional;

public interface IRentalRepository {
    List<RentalDto> getAll();

    Optional<RentalDto> getById(Long rentalId);
    Optional<RentalDto> getByUserId(Long userId);

    RentalDto save(RentalDto rentalDto, String newStatus);
}