package com.gcatechnologies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usersId")
    private Long usersId;

    @Column(name = "method_payment_Id")
    private Long methodPaymentId;

    private Double valueRental;

    private String vehicle; // OBJECTO

    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateStart;

    @Column(name = "date_finalized")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateFinalized;

    private String status;
    //private Vehicle vehicleEntity;

    @ManyToOne()
    @JoinColumn(name = "method_payment_Id", insertable = false, updatable = false)
    private MethodPayment methodPaymentEntity;

    @ManyToOne()
    @JoinColumn(name = "usersId", insertable = false, updatable = false)
    private Users users;
}