package com.gcatechnologies.repositoriesCrudJpa;

import com.gcatechnologies.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepositoryCrudJpa extends JpaRepository<Vehicle, Long> {

}