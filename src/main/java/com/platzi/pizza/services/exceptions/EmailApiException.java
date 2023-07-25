package com.platzi.pizza.services.exceptions;

public class EmailApiException extends RuntimeException{
    public EmailApiException(){
        super("Error Send Email...");
    }
}
