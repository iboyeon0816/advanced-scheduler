package com.example.scheduler2.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final int status;

    private final String title;

    private String detail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> validationErrors;

    public ErrorResponse(int status, String title, String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
    }

    public static ErrorResponse of(HttpStatusCode status, String detail, Map<String, String> errors) {
        HttpStatus httpStatus = (HttpStatus) status;
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), detail, errors);
    }

    public static ErrorResponse of(HttpStatusCode status, String detail) {
        HttpStatus httpStatus = (HttpStatus) status;
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), detail);
    }
}
