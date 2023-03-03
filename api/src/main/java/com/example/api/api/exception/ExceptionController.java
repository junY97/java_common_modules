package com.example.api.api.exception;

import com.example.api.api.http.response.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;

import static com.example.api.api.exception.ErrorCode.INTERNAL_SERVER_ERROR;

/**
 * @author junyeong.jo .
 * @since 2023-02-27
 */
@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * InvalidRequest Exception
     * Error Status (4xx)
     */
    @ExceptionHandler({InvalidRequestException.class})
    public ErrorResponse customExceptionHandler (InvalidRequestException e) {
        logger.error(String.valueOf(e));

        ErrorCode errorCode = e.getErrorCode();

        return new ErrorResponse(errorCode, e.debugMessage, errorCode.getCode());
    }


    /**
     * Server Exception
     * Error Status (500)
     */
    @ExceptionHandler({ServerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse customExceptionHandler (ServerException e) {
        logger.error(String.valueOf(e));

        ErrorCode errorCode = e.getErrorCode();

        return new ErrorResponse(errorCode, e.debugMessage, errorCode.getCode());
    }

    /**
     * Internal Server Error (Global)
     * Error Status (500)
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse customExceptionHandler (Exception e) {
        logger.error(String.valueOf(e));

        ErrorCode errorCode = INTERNAL_SERVER_ERROR;

        return new ErrorResponse(errorCode, e.getMessage(), errorCode.getCode());
    }
}
