package com.study.multiserverlogin.error.message;

import com.study.multiserverlogin.login.LoginStatus;

public interface BaseExceptionMessage {
    LoginStatus getLoginStatus();
    int getHttpStatus();
    String getErrorMessage();
}
