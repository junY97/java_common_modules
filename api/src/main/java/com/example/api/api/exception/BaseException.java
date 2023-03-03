package com.example.api.api.exception;

import lombok.Getter;
import org.springframework.core.NestedRuntimeException;

/**
 * @author junyeong.jo .
 * @since 2023-03-03
 */
@Getter
abstract class BaseException extends NestedRuntimeException {
    ErrorCode errorCode;
    String debugMessage;

    public BaseException(ErrorCode errorCode, String debugMessage) {
        super(debugMessage);
        this.errorCode = errorCode;
        this.debugMessage = debugMessage;
    }


}
