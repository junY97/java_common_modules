package com.example.api.api.exception;

import lombok.Getter;

/**
 * @author junyeong.jo .
 * @since 2023-02-27
 */
@Getter
public class CustomException extends RuntimeException {
   ErrorCode errorCode;
   String debugMessage;

    public CustomException(ErrorCode errorCode, String debugMessage) {
        this.errorCode = errorCode;
        this.debugMessage = debugMessage;
    }

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

