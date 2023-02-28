package com.example.api.api.http.response;

import com.example.api.api.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * ErrorResponse represents an error response returned by the API
 *  @author junyeong.jo .
 *  @since 2023-02-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus errorStatus;
    private String interfaceMessage;
    private boolean success;
    private LocalDateTime responseTime;
    private String debugMessage;
    private int errorCode;

    /**
     Constructs an error response object.
     @param error error code and message of the error.
     @param debugMessage debug message for the error.
     @param errorCode error code of the error.
     */
    public ErrorResponse(ErrorCode error, String debugMessage, int errorCode) {
        this.errorStatus = error.getHttpStatus();
        this.interfaceMessage = error.getMessage();
        this.success = false;
        this.responseTime = LocalDateTime.now();
        this.debugMessage = debugMessage;
        this.errorCode = errorCode;
    }

    /**
     Constructs an error response object.
     @param error error code and message of the error.
     @param errorCode error code of the error.
     */
    public ErrorResponse(ErrorCode error, int errorCode) {
        this.errorStatus = error.getHttpStatus();
        this.interfaceMessage = error.getMessage();
        this.success = false;
        this.responseTime = LocalDateTime.now();
        this.debugMessage = "";
        this.errorCode = errorCode;
    }
}