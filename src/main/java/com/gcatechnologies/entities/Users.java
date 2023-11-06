package com.gcatechnologies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String lastName;
    private String password;

    @Column(name = "date_registration")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateRegistration;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.ALL})
    private List<MethodPayment> methodPaymentList;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.ALL})
    private List<Rental> rentalList;
}