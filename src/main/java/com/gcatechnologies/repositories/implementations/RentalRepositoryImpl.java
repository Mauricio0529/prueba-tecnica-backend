package com.gcatechnologies.repositories.implementations;

import com.gcatechnologies.constants.StatusRentalConstants;
import com.gcatechnologies.dto.RentalDto;
import com.gcatechnologies.entities.Rental;
import com.gcatechnologies.repositories.contracts.IRentalRepository;
import com.gcatechnologies.repositories.mapper.MapperRental;
import com.gcatechnologies.repositoriesCrudJpa.RentalRepositoryCrudJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepositoryImpl implements IRentalRepository {

    private final RentalRepositoryCrudJpa rentalRepositoryCrudJpa;

    private final MapperRental mapperRental;

    @Override
    public List<RentalDto> getAll() {
        return mapperRental.toDtoLis(rentalRepositoryCrudJpa.findAll());
    }

    @Override
    public Optional<RentalDto> getById(Long rentalId) {
        return rentalRepositoryCrudJpa.findById(rentalId).map(mapperRental::toDto);
    }

    @Override
    public Optional<RentalDto> getByUserId(Long userId) {
       return rentalRepositoryCrudJpa.findByUsersId(userId).map(mapperRental::toDto);
    }

    @Override
    public RentalDto save(RentalDto rentalDto, String newStatus) {
        rentalDto.setDateStart(LocalDateTime.now());
        Rental rentalEntity = rentalRepositoryCrudJpa.save(mapperRental.toEntity(newStatusRental(rentalDto, newStatus)));
        return mapperRental.toDto(rentalEntity);
    }

    private RentalDto newStatusRental(RentalDto rentalDto, String newStatus) {
        rentalDto.setStatus(newStatus);
        if (rentalDto.getStatus().equals(StatusRentalConstants.CLOSED)) {
            rentalDto.setDateFinalized(LocalDateTime.now());
        }
        return rentalDto;
    }
}