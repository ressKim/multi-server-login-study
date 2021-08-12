package com.study.multiserverlogin.response.login;

import com.study.multiserverlogin.login.LoginStatus;
import com.study.multiserverlogin.response.BaseResponse;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value()
public class LoginExceptionResponse<T> implements BaseResponse {

    LoginStatus status;

    String message;

    T result;

    public static <T> LoginExceptionResponse<T> loginError(String message, T result) {
        return new LoginExceptionResponse<>(LoginStatus.FAIL, message, result);
    }

}
