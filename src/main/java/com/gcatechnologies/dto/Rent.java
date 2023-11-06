package com.gcatechnologies.dto;

import java.time.LocalDateTime;

public class Rent {
    /**
     * RESERVA
     */
    private Double valueRent;
    private String Vehicle;

    /**
     metodo de pago que hizo el usuario
     Texto
     Objeto
     */
    private String methodPayUsers;

    private String statusRental;

    private LocalDateTime startDate;
    private LocalDateTime finishDate;
}
