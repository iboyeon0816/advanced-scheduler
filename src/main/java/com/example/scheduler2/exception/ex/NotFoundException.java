package com.example.scheduler2.exception.ex;

import com.example.scheduler2.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class NotFoundException extends GeneralException {
    public NotFoundException(String resourceName, Long id) {
        super(
                HttpStatus.NOT_FOUND,
                String.format("%s with ID %s not found.", StringUtils.capitalize(resourceName), id)
        );
    }
}
