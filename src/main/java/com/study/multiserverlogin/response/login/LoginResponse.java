package com.study.multiserverlogin.response.login;

import com.study.multiserverlogin.login.LoginStatus;
import com.study.multiserverlogin.response.BaseResponse;
import lombok.Value;

@Value()
public class LoginResponse<T> implements BaseResponse {


    LoginStatus status;

    String message;

    T result;


    public static <T> LoginResponse<T> success(String message, T result) {
        return new LoginResponse<>(LoginStatus.SUCCESS, message, result);
    }

    public static <T> LoginResponse<T> fail(String message, T result) {
        return new LoginResponse<>(LoginStatus.FAIL, message, result);
    }

    public static <T> LoginResponse<T> needLogin(String message, T result) {
        return new LoginResponse<>(LoginStatus.FAIL, message, result);
    }
}
