package com.example.api.api.exception;

import lombok.Getter;

/**
 * @author junyeong.jo .
 * @since 2023-03-03
 */
@Getter
public class ServerException extends BaseException{
    String debugMessage;
    ErrorCode errorCode;
    public ServerException(String debugMessage, ErrorCode errorCode) {
        super(debugMessage, errorCode);
        this.debugMessage = debugMessage;
        this.errorCode = errorCode;
    }
    public ServerException(ErrorCode errorCode) {
        super("", errorCode);
        this.errorCode = errorCode;
    }
}
