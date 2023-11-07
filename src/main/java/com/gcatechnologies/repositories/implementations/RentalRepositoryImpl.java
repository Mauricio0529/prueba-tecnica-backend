package com.gcatechnologies.repositories.implementations;

import com.gcatechnologies.constants.StatusRentalConstants;
import com.gcatechnologies.dto.RentalDto;
import com.gcatechnologies.entities.Rental;
import com.gcatechnologies.repositories.contracts.IRentalRepository;
import com.gcatechnologies.repositories.mapper.MapperRental;
import com.gcatechnologies.repositories.mapper.MapperVehicle;
import com.gcatechnologies.repositoriesCrudJpa.RentalRepositoryCrudJpa;
import jakarta.transaction.Transactional;
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
    private final MapperVehicle mapperVehicle;

    @Override
    public List<RentalDto> getAll() {
        List<Rental> rentalEntities = rentalRepositoryCrudJpa.findAll();
        return mapperRental.toDtoLis(rentalEntities);
    }

    @Override
    public Optional<RentalDto> getById(Long rentalId) {
        return rentalRepositoryCrudJpa.findById(rentalId).map(mapperRental::toDto);
    }

    @Override
    public Optional<RentalDto> getByUserId(Long userId) {
       return rentalRepositoryCrudJpa.findByUsersId(userId).map(mapperRental::toDto);
    }

    @Transactional
    @Override
    public RentalDto save(RentalDto rentalDto, String newStatus) {
        rentalDto.setDateStart(LocalDateTime.now());
        rentalDto.setStatus(newStatusRental(rentalDto, newStatus).getStatus());

        Rental rentalEntity = mapperRental.toEntity(rentalDto);

        Long rentId = rentalRepositoryCrudJpa.save(rentalEntity).getId();

        Rental rentalSaved = rentalRepositoryCrudJpa.findById(rentId).get();

        rentalSaved.getVehiclesList().forEach(vehicleDto -> vehicleDto.setRentalId(rentalSaved.getId()));

        rentalRepositoryCrudJpa.save(rentalSaved);

        return mapperRental.toDto(rentalSaved);
    }

    /**
     * Establecer el estado y validar la fecha de finalizacion si el estado es Cerrado
     * @param rentalDto Alquiler a cambiar su estado
     * @param newStatus Nuevo estado a establecer
     * @return Objecto Alquiler con su nuevo estado
     */
    private RentalDto newStatusRental(RentalDto rentalDto, String newStatus) {
        rentalDto.setStatus(newStatus);
        if (rentalDto.getStatus().equals(StatusRentalConstants.CLOSED)) {
            rentalDto.setDateFinalized(LocalDateTime.now());
        }
        return rentalDto;
    }
}