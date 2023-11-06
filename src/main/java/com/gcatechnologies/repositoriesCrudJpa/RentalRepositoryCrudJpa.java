package com.gcatechnologies.repositoriesCrudJpa;

import com.gcatechnologies.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepositoryCrudJpa extends JpaRepository<Rental, Long> {
    Optional<Rental> findByUsersId(Long userId);
}