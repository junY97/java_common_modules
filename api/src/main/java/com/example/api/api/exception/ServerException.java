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
    public ServerException(ErrorCode errorCode, String debugMessage) {
        super(errorCode, debugMessage);
        this.errorCode = errorCode;
        this.debugMessage = debugMessage;
    }
    public ServerException(ErrorCode errorCode) {
        super(errorCode, "");
        this.errorCode = errorCode;
    }
}
