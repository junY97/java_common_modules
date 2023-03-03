package com.example.api.api.exception;

/**
 * @author junyeong.jo .
 * @since 2023-03-03
 */

public class ExceptionBuilders {
    public void invalidRequest (String debugMessage,ErrorCode errorCode) {
        throw new InvalidRequestException(debugMessage, errorCode);
    }
    public void invalidRequest (ErrorCode errorCode) {
        throw new InvalidRequestException(errorCode);
    }

    public void serverError (String debugMessage,ErrorCode errorCode) {
        throw new ServerException(debugMessage, errorCode);
    }
    public void serverError (ErrorCode errorCode) {
        throw new ServerException(errorCode);
    }
}
