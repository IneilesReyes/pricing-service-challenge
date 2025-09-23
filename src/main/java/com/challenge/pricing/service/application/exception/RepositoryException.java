package com.challenge.pricing.service.application.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
