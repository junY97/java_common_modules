package com.example.api.api.exception;

import com.example.api.api.http.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    public ResponseEntity<ErrorResponse> customExceptionHandler (InvalidRequestException e) {
        logger.error(String.valueOf(e));

        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, e.debugMessage, errorCode.getCode());
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }


    /**
     * Server Exception
     * Error Status (5xx)
     */
    @ExceptionHandler({ServerException.class})
    protected ResponseEntity<ErrorResponse> customExceptionHandler (ServerException e) {
        logger.error(String.valueOf(e));

        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, e.debugMessage, errorCode.getCode());
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }

    /**
     * Internal Server Error (Global)
     * Error Status (500)
     */
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> customExceptionHandler (Exception e) {
        logger.error(String.valueOf(e));

        ErrorCode errorCode = INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(errorCode, e.getMessage(), errorCode.getCode());
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }
}
