package com.example.scheduler2.exception.ex;

import com.example.scheduler2.exception.GeneralException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends GeneralException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
