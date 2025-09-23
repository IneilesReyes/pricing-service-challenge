package com.challenge.pricing.service.infrastructure.controller;

import com.challenge.pricing.service.application.exception.RepositoryException;
import com.challenge.pricing.service.application.exception.ResourceNotFoundException;
import com.challenge.pricing.service.infrastructure.controller.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Invalid value '%s' for parameter '%s'.", ex.getValue(), ex.getName());
        return buildErrorResponse(message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String message = String.format("Missing value for parameter '%s'.", ex.getParameterName());
        return buildErrorResponse(message);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFound(Exception ex) {
        return handleErrorResponse(ex, ex.getMessage());
    }


    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBadUrlRequest(NoResourceFoundException ex){
        return handleErrorResponse(ex, "The requested resource was not found.");
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handleDataAccessResourceFailure(RepositoryException ex) {
        return handleErrorResponse(ex,
                "Database connection failed or persistence error occurred.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex) {
        return handleErrorResponse(ex, "An unexpected error occurred. Please try again later.");
    }

    private ErrorResponse handleErrorResponse(Throwable throwable, String message) {
        log.error("Encountered {} error: {}", throwable.getClass().getCanonicalName(), throwable.getMessage(), throwable);
        return buildErrorResponse(message);
    }

    private ErrorResponse buildErrorResponse(String message) {
        return new ErrorResponse(message, LocalDateTime.now());
    }
}