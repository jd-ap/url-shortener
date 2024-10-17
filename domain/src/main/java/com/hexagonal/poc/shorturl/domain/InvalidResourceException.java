package com.hexagonal.poc.shorturl.domain;

public final class InvalidResourceException extends Exception {
    public static InvalidResourceException valueRequired() {
        return new InvalidResourceException("Resource is null");
    }

    public static InvalidResourceException valueMalformed() {
        return new InvalidResourceException("Resource is malformed");
    }

    public static <T extends Exception> InvalidResourceException from(T cause) {
        return new InvalidResourceException(cause.getMessage(), cause);
    }

    public InvalidResourceException(String message) {
        super(message);
    }

    public InvalidResourceException(String message, Exception cause) {
        super(message, cause);
    }

}
