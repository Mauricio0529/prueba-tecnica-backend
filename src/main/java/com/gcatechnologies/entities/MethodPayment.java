package com.gcatechnologies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "method_payment")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usersId")
    private Long usersId;

    private String typePayment;

    private Integer numberCard;

    @ManyToOne()
    @JoinColumn(name = "usersId", insertable = false, updatable = false)
    private Users users;
}