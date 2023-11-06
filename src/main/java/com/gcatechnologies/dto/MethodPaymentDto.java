package com.gcatechnologies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodPaymentDto {

    private Long id;
    private Long usersId;
    private String typePayment;
    private Integer numberCard;

}