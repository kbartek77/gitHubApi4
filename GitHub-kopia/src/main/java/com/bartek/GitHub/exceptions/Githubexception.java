package com.bartek.GitHub.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
public abstract class Githubexception extends RuntimeException {
    private final HttpStatus httpStatus;
    public Githubexception(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

