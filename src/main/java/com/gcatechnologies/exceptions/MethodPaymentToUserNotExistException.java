package com.gcatechnologies.exceptions;

public class MethodPaymentToUserNotExistException extends RuntimeException{
    public MethodPaymentToUserNotExistException() {
        super("El medio de pago no existe en la cuenta de usuario.");
    }
}