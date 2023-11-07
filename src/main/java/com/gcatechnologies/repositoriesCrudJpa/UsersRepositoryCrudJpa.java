package com.gcatechnologies.repositoriesCrudJpa;

import com.gcatechnologies.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositoryCrudJpa extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}