package com.example.api.api.exception;

import lombok.Getter;

/**
 * @author junyeong.jo .
 * @since 2023-03-03
 */
@Getter
public class InvalidRequestException extends BaseException {
    String debugMessage;
    ErrorCode errorCode;
    public InvalidRequestException(String debugMessage, ErrorCode errorCode) {
        super(debugMessage, errorCode);
        this.debugMessage = debugMessage;
        this.errorCode = errorCode;
    }
    public InvalidRequestException(ErrorCode errorCode) {
        super("", errorCode);
        this.errorCode = errorCode;
    }
}
