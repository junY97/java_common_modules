package com.example.api.api.exception;

import com.example.api.api.http.response.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;

import static com.example.api.api.exception.ErrorCode.INTERNAL_SERVER_ERROR;

/**
 * @author junyeong.jo .
 * @since 2023-02-27
 */
@RestControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
    * Custom Exception
    */
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponse> customExceptionHandler (CustomException e) {
        logger.error(e.debugMessage);

        ErrorCode errorCode = e.getErrorCode();
        HttpStatus httpStatus = e.getErrorCode().getHttpStatus();
        int code = e.getErrorCode().getCode();

        if (e.getDebugMessage() != null) {
            String debugMessage = e.getDebugMessage();
            return new ResponseEntity<> (new ErrorResponse(errorCode, debugMessage, code), httpStatus);
        } else {
            return new ResponseEntity<> (new ErrorResponse(errorCode, code), httpStatus);
        }
    }

    /**
     * Global Exception
     * INTERNAL SEVER ERROR (500 ERROR)
     */
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> customExceptionHandler (Exception e) {
        logger.error(e.getMessage());

        HttpStatus httpStatus = INTERNAL_SERVER_ERROR.getHttpStatus();
        int code = INTERNAL_SERVER_ERROR.getCode();
        String debugMessage = e.getMessage();

        return new ResponseEntity<> (new ErrorResponse(INTERNAL_SERVER_ERROR, debugMessage, code), httpStatus);
    }
}
