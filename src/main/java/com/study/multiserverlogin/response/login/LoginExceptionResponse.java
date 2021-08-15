package com.study.multiserverlogin.response.login;

import com.study.multiserverlogin.error.message.LoginCheckExceptionMessage;
import com.study.multiserverlogin.login.LoginStatus;
import com.study.multiserverlogin.response.BaseResponse;
import lombok.Value;

@Value()
public class LoginExceptionResponse implements BaseResponse {

    LoginStatus status;
    int httpCode;
    String errorMessage;

    public static LoginExceptionResponse loginCheckError(LoginCheckExceptionMessage e) {
        return new LoginExceptionResponse(e.getLoginStatus(), e.getHttpStatus(), e.getErrorMessage());
    }

}
