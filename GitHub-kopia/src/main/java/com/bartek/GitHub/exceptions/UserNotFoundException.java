package com.bartek.GitHub.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Githubexception{
    public UserNotFoundException (String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
