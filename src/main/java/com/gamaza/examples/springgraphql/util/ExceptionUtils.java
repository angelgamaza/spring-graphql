package com.gamaza.examples.springgraphql.util;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import io.micrometer.common.util.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.validation.BindException;

/**
 * Utils for exceptions
 */
public final class ExceptionUtils {

    /**
     * Get the cause or the default message from the given {@link Throwable}
     *
     * @param throwable The {@link Throwable} instance
     * @return The first non-blank message or the class name if none found
     */
    public static String getCauseOrDefaultMessage(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        String message = ExceptionUtils.getFirstNonBlank(
                (throwable.getCause() != null) ? throwable.getCause().getLocalizedMessage() : null,
                throwable.getLocalizedMessage(),
                throwable.getMessage()
        );
        return message != null ? message : throwable.getClass().getSimpleName();
    }

    /**
     * Get the first non-blank string from the provided messages
     *
     * @param messages Varargs of messages
     * @return The first non-blank message or null if none found
     */
    private static String getFirstNonBlank(String... messages) {
        for (String message : messages) {
            if (!StringUtils.isBlank(message)) {
                return message;
            }
        }
        return null;
    }

    /**
     * Check if the exception is a validation exception
     *
     * @param exception The exception to check
     * @return True if the exception is a validation exception
     */
    public static boolean isValidationException(Throwable exception) {
        return exception instanceof IllegalArgumentException || exception instanceof BindException;
    }

    /**
     * Check if the exception is a DataIntegrityViolationException
     *
     * @param exception The exception to check
     * @return True if the exception is a DataIntegrityViolationException
     */
    public static boolean isDataIntegrityViolationExceptionType(Throwable exception) {
        return exception instanceof DataIntegrityViolationException
                || exception instanceof InvalidDataAccessApiUsageException
                || exception instanceof ConstraintViolationException;
    }

    /**
     * Build a response for the catched error
     *
     * @param errorType   The Error type
     * @param message     The Error Message
     * @param environment The Data fetching environment
     * @return The built GraphQLError
     */
    public static GraphQLError buildGraphQlError(ErrorClassification errorType, String message, DataFetchingEnvironment environment) {
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(message)
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }

    /**
     * Private constructor to prevent instantiation
     */
    private ExceptionUtils() {
        throw new IllegalStateException("Could not instantiate ExceptionUtils class!");
    }

}