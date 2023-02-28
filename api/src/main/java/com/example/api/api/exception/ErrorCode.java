package com.example.api.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author junyeong.jo .
 * @since 2023-02-27
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 BAD_REQUEST
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터를 확인해주세요", HttpStatus.BAD_REQUEST.value()),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());

    private final HttpStatus httpStatus;
    private final String message;
    private final int code;
}
