package com.gamaza.examples.graphql.config;

import com.gamaza.examples.graphql.exception.NotFoundException;
import com.gamaza.examples.graphql.util.ExceptionUtils;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import static graphql.ErrorType.ExecutionAborted;
import static graphql.ErrorType.ValidationError;
import static org.springframework.graphql.execution.ErrorType.INTERNAL_ERROR;
import static org.springframework.graphql.execution.ErrorType.NOT_FOUND;


/**
 * Global Exception Handler
 */
@Component
@NonNullApi
public class GlobalExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable throwable, DataFetchingEnvironment environment) {
        // Error message from the Exception
        String errorMessage = ExceptionUtils.getCauseOrDefaultMessage(throwable);
        // Exception threatment
        if (throwable instanceof NotFoundException) {
            return ExceptionUtils.buildGraphQlError(NOT_FOUND, errorMessage, environment);
        }
        if (ExceptionUtils.isDataIntegrityViolationExceptionType(throwable)) {
            return ExceptionUtils.buildGraphQlError(ExecutionAborted, errorMessage, environment);
        }
        if (ExceptionUtils.isValidationException(throwable)) {
            return ExceptionUtils.buildGraphQlError(ValidationError, errorMessage, environment);
        }
        return ExceptionUtils.buildGraphQlError(INTERNAL_ERROR, errorMessage, environment);
    }

}
