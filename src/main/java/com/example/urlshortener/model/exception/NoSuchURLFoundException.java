package com.example.urlshortener.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchURLFoundException extends RuntimeException {
    public NoSuchURLFoundException(String urlString) {
        super("URL is not found: " + urlString);
    }
}
