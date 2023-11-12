package com.gcatechnologies.repositoriesCrudJpa;

import com.gcatechnologies.entities.MethodPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MethodPaymentRepositoryCrudJpa extends JpaRepository<MethodPayment, Long> {
    List<MethodPayment> findByUsersId(Long userId);
    Integer countByNumberCard(Integer numberCard);
}