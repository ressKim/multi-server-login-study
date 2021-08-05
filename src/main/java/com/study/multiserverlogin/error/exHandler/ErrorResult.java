package com.study.multiserverlogin.error.exHandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResult {
    private String errorCode;
    private String message;


    public static ErrorResult of(String errorCode, String message) {
        return new ErrorResult(errorCode, message);
    }

    private ErrorResult(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
