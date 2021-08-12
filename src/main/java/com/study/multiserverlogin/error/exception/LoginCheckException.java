package com.study.multiserverlogin.error.exception;

/**
 * login 안 된 사용자 check exception
 */
public class LoginCheckException extends RuntimeException{
    public LoginCheckException(String message) {
        super(message);
    }
}
