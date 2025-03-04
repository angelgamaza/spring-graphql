package com.gamaza.examples.springgraphql.exception;

import java.io.Serial;

/**
 * Exception for Already Exists cases
 */
public class AlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5691907096268098225L;

    // Class constants
    private static final String ALREADY_EXISTS_MESSAGE = "The object [%s] with parameters [%s] already exists!";

    /**
     * Constructor
     *
     * @param object     The object type
     * @param parameters The object parameters
     */
    public AlreadyExistsException(String object, String parameters) {
        super(
                String.format(ALREADY_EXISTS_MESSAGE, object, parameters)
        );
    }

}
