package com.example.scheduler2.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class OptionalUtils {
    public static <T> T getOrThrowNotFound(Optional<T> entity) {
        return entity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public static <T> T getOrThrowUnauthorized(Optional<T> entity) {
        return entity.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
}
