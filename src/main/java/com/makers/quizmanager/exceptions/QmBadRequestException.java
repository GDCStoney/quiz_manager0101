package com.makers.quizmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QmBadRequestException extends RuntimeException {

    public QmBadRequestException(String message) {
        super(message);
    }
}
