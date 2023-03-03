package com.example.api.api.exception;

import lombok.Getter;

/**
 * @author junyeong.jo .
 * @since 2023-03-03
 */
@Getter
public class ExceptionBuilders {
    public static void invalidRequest (ErrorCode errorCode, String debugMessage) throws InvalidRequestException {
         throw new InvalidRequestException(errorCode, debugMessage);
    }
    public static void invalidRequest(ErrorCode errorCode) throws InvalidRequestException  {
        throw new InvalidRequestException(errorCode);
    }

    public static void serverError (ErrorCode errorCode, String debugMessage) throws ServerException  {
        throw new ServerException(errorCode, debugMessage);
    }
    public static void serverError (ErrorCode errorCode) throws ServerException {
        throw new ServerException(errorCode);
    }
}
