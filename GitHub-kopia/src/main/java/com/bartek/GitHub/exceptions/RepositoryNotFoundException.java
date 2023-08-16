package com.bartek.GitHub.exceptions;

import com.bartek.GitHub.model.Entity.GitHubUser;
import org.springframework.http.HttpStatus;

public class RepositoryNotFoundException extends Githubexception {
    public RepositoryNotFoundException (String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
