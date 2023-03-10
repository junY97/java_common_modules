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
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터를 확인해주세요", 400),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "어세스 토큰이 유효하지 않습니다", 400),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러입니다.", 500);

    private final HttpStatus httpStatus;
    private final String message;
    private final int code;
}
