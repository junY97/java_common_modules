package com.example.api.api.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author junyeong.jo .
 * @since 2023-02-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse <T> {
    private T data;
    private HttpStatus httpStatus;
    private Boolean success;

    public SuccessResponse(T data, HttpStatus httpStatus) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.success = true;
    }
}
