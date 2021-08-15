package com.study.multiserverlogin.error.message;

import com.study.multiserverlogin.login.LoginStatus;
import lombok.Getter;

import static com.study.multiserverlogin.login.LoginStatus.*;

@Getter
public enum LoginCheckExceptionMessage implements BaseExceptionMessage {

    NEED_LOGIN(FAIL, 200, "로그인이 필요합니다"),
    NOT_FOUND_USER(FAIL, 200, "해당하는 사용자가 존재하지 않습니다."),
    DUPLICATED_USER(FAIL, 200, "이미 존재하는 사용자 아이디입니다."),
    WRONG_PASSWORD(FAIL, 200, "패스워드를 잘못 입력하였습니다.");

    private final LoginStatus loginStatus;
    private final int httpStatus;
    private final String errorMessage;

    LoginCheckExceptionMessage(LoginStatus status, int httpStatus, String errorMessage) {
        this.loginStatus = status;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }


}
