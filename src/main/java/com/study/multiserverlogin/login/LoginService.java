package com.study.multiserverlogin.login;

import com.study.multiserverlogin.response.login.LoginResponse;
import com.study.multiserverlogin.user.UserValue;

import javax.servlet.http.HttpSession;

public interface LoginService {

    boolean login(UserValue userValue, HttpSession session);

//    LoginResponse loginCheck(HttpSession session);

    boolean isLoginCheck(HttpSession session);
}
