package com.example.scheduler2.exception.ex;

import com.example.scheduler2.exception.GeneralException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends GeneralException {
    public ForbiddenException(String resourceName) {
        super(
                HttpStatus.FORBIDDEN,
                "You do not have permission to modify this " + resourceName.toLowerCase()
        );
    }
}
