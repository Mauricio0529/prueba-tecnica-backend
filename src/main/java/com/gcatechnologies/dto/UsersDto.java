package com.gcatechnologies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String password;

    private LocalDateTime dateRegistration;

    private List<MethodPaymentDto> methodPaymentList;
}