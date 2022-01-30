package com.makers.quizmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QmResourceNotFoundException extends RuntimeException{

    public QmResourceNotFoundException(String message) {
        super(message);
    }
}
