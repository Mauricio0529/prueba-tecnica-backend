package com.gcatechnologies.exceptions;

public class ValidatedNumberCard extends RuntimeException {
    public ValidatedNumberCard() {
        super("Por favor ingrese el numero de la tarjeta.");
    }
}