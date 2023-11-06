package com.gcatechnologies.services.implementations;

import com.gcatechnologies.constants.StatusRentalConstants;
import com.gcatechnologies.dto.RentalDto;
import com.gcatechnologies.repositories.contracts.IRentalRepository;
import com.gcatechnologies.services.contracts.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements IRentalService {

    private final IRentalRepository iRentalRepository;

    @Override
    public List<RentalDto> getAll() {
        return iRentalRepository.getAll();
    }

    @Override
    public Optional<RentalDto> getById(Long rentalId) {
        Optional<RentalDto> rentalDtoOptional = iRentalRepository.getById(rentalId);
        if (rentalDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        return rentalDtoOptional;
    }

    @Override
    public Optional<RentalDto> getByUserId(Long userId) {
        Optional<RentalDto> rentalDtoOptional = iRentalRepository.getByUserId(userId);
        if (rentalDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        return rentalDtoOptional;
    }

    @Override
    public RentalDto save(RentalDto rentalDto) {
        String newStatus = StatusRentalConstants.OPEN;
        return iRentalRepository.save(rentalDto, newStatus);
    }

    @Override
    public Optional<RentalDto> updateStatus(Long rentalId, String newStatus) {
        RentalDto rentalDto = getById(rentalId).get();
        if(rentalDto == null) {
            return Optional.empty();
        }
        iRentalRepository.save(rentalDto, newStatus);

        return Optional.of(rentalDto);
    }
}
