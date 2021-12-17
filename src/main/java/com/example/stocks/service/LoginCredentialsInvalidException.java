package com.example.stocks.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LoginCredentialsInvalidException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public LoginCredentialsInvalidException(String message) {
        super(message);
    }
}
