package com.bartek.GitHub.exceptionHandler;

import com.bartek.GitHub.exceptions.Githubexception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GeneralException {
    @ExceptionHandler(Githubexception.class)
    public ResponseEntity<String> handleGitHubException(Githubexception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
