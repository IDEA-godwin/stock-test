package com.example.stocks.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
