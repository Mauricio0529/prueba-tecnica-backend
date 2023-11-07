package com.gcatechnologies.exceptions;

public class UsersNotExistException extends RuntimeException {

    public UsersNotExistException() {
        super("El usuario ingresado no existe.");
    }
}