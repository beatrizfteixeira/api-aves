package com.grupo4.APIAvesdoBrasil.exception;

import org.springframework.http.HttpStatus;

public class BirdAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BirdAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BirdAPIException(String message, HttpStatus status, String message1) {
        super("Deu Erro");
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
