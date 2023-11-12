package com.gcatechnologies.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    public UserNameAlreadyExistsException() {
        super("El nombre de usuario ingresado ya existe");
    }
}