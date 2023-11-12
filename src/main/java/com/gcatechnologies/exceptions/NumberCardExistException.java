package com.gcatechnologies.exceptions;

public class NumberCardExistException extends RuntimeException{
    public NumberCardExistException () {
        super("El numero de tarjeta ya existe.");
    }
}