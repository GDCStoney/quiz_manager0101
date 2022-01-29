package com.makers.quizmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class QmAuthException extends RuntimeException {

    public QmAuthException(String message) {
        super(message);
    }
}
