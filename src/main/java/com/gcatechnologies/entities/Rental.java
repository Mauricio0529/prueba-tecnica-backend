package com.gcatechnologies.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usersId")
    private Long usersId;

    @Column(name = "method_payment_Id")
    private Long methodPaymentId;

    private Double valueRental;

    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateStart;

    @Column(name = "date_finalized")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateFinalized;

    private Vehicle vehicleEntity;

    @ManyToOne()
    @JoinColumn(name = "method_payment_Id", insertable = false, updatable = false)
    private MethodPayment methodPaymentEntity;

    @ManyToOne()
    @JoinColumn(name = "usersId", insertable = false, updatable = false)
    private Users users;
}