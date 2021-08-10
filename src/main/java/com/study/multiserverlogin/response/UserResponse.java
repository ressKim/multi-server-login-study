package com.study.multiserverlogin.response;

import lombok.Value;

@Value()
public class UserResponse<T> implements BaseResponse {

    UserStatus status;

    String message;

    T result;

    enum UserStatus {
        SUCCESS, FAIL
    }


    public static <T> UserResponse<T> success(String message, T result) {
        return new UserResponse<>(UserStatus.SUCCESS, message, result);
    }

    public static <T> UserResponse<T> fail(String message, T result) {
        return new UserResponse<>(UserStatus.FAIL, message, result);
    }

}
