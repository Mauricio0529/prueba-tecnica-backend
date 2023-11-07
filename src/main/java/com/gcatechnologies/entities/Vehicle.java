package com.gcatechnologies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rentalId")
    private Long rentalId;

    private String nameVehicle;
    private String modelYear;

    private Double priceVehicleRental;

    @ManyToOne()
    @JoinColumn(name = "rentalId", insertable = false, updatable = false)
    private Rental rental;
}