package com.gcatechnologies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

    /**
     * RESERVA
     */
    private Long id;
    private Long usersId;
    /**
     metodo de pago que hizo el usuario
     Texto
     Objeto
     */
    private Long methodPaymentId;

    private Double valueRental;

    private String Vehicle; // OBJECTO

    private LocalDateTime dateStart;
    private LocalDateTime dateFinalized;

    private String status;
}