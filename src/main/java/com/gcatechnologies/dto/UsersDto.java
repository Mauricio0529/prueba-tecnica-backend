package com.gcatechnologies.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class UsersDto {

    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String password;

    private LocalDateTime dateRegistration;

    private List<MethodPaymentDto> methodPaymentList;
}