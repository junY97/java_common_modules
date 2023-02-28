package com.example.api.api.exception;

import com.example.api.api.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.example.api.api.exception.ErrorCode.INTERNAL_SERVER_ERROR;

/**
 * @author junyeong.jo .
 * @since 2023-02-27
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponse> customExceptionHandler (CustomException e) {
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
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> customExceptionHandler (Exception e) {
        HttpStatus httpStatus = INTERNAL_SERVER_ERROR.getHttpStatus();
        int code = INTERNAL_SERVER_ERROR.getCode();
        String debugMessage = e.getMessage();
        return new ResponseEntity<> (new ErrorResponse(INTERNAL_SERVER_ERROR, debugMessage, code), httpStatus);
    }
}
