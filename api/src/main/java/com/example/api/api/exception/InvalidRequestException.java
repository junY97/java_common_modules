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
    public InvalidRequestException(ErrorCode errorCode, String debugMessage) {
        super(errorCode, debugMessage);
        this.errorCode = errorCode;
        this.debugMessage = debugMessage;
    }
    public InvalidRequestException(ErrorCode errorCode) {
        super(errorCode, "");
        this.errorCode = errorCode;
    }
}
