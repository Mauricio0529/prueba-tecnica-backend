package com.gcatechnologies.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RentalDto {

    private Long id;
    private Long usersId;

    private Long methodPaymentId;

    private Double valueRental;

    // private String vehicle; // OBJECTO

    private LocalDateTime dateStart;
    private LocalDateTime dateFinalized;

    private String status;

    private List<VehicleDto> vehiclesList;
}