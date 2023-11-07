package com.gcatechnologies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MethodPaymentDto {

    private Long id;
    private Long usersId;
    private String typePayment;
    private Integer numberCard;

}