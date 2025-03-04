package com.gamaza.examples.springgraphql.exception;

import java.io.Serial;

/**
 * Exception for Generic cases
 */
public class GenericRuntimeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8646119051695603867L;

    // Class constants
    private static final String MESSAGE = "Unhandled exception with message: %s";

    /**
     * Constructor
     *
     * @param message The exception message
     */
    public GenericRuntimeException(String message) {
        super(
                String.format(MESSAGE, message)
        );
    }

}
