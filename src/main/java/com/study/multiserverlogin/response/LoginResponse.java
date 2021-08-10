package com.study.multiserverlogin.response;

import lombok.Value;

@Value()
public class LoginResponse<T> implements BaseResponse {


    LoginStatus status;

    String message;

    String requestUri;

    T result;

    enum LoginStatus {
        SUCCESS, FAIL
    }


    public static <T> LoginResponse<T> success(String message, T result) {
        return new LoginResponse<>(LoginStatus.SUCCESS, message, null, result);
    }

    public static <T> LoginResponse<T> fail(String message, T result) {
        return new LoginResponse<>(LoginStatus.FAIL, message, null, result);
    }

    public static <T> LoginResponse<T> needLogin(String message, String requestUri, T result) {
        return new LoginResponse<>(LoginStatus.FAIL, message, requestUri, result);
    }
}
