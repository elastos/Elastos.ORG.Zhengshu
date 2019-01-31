package org.elastos.exception;

public class ElastosServiceException extends RuntimeException {
    public ElastosServiceException(String message) {
        super(message);
    }

    public ElastosServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
