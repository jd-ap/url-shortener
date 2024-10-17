package com.hexagonal.poc.shorturl.application.control;

public class EmptyValueResultException extends RuntimeException {
    public EmptyValueResultException(String message) {
        super(message);
    }
}
