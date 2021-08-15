package com.study.multiserverlogin.error.exception;

import com.study.multiserverlogin.error.message.LoginCheckExceptionMessage;
import lombok.Getter;

/**
 * login 안 된 사용자 check exception
 */
public class LoginCheckException extends RuntimeException {

    @Getter
    private final LoginCheckExceptionMessage loginCheckExceptionMessage;

    public static LoginCheckException create(LoginCheckExceptionMessage loginCheckExceptionMessage) {
        return new LoginCheckException(loginCheckExceptionMessage);
    }

    private LoginCheckException(LoginCheckExceptionMessage loginCheckExceptionMessage) {
        super(loginCheckExceptionMessage.getErrorMessage());
        this.loginCheckExceptionMessage = loginCheckExceptionMessage;
    }
}
