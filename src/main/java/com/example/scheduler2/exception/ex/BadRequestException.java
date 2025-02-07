package com.example.scheduler2.exception.ex;

import com.example.scheduler2.exception.GeneralException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends GeneralException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
