package com.study.multiserverlogin.login;

import com.study.multiserverlogin.user.UserValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    boolean login(UserValue userValue, HttpServletResponse response);

//    LoginResponse loginCheck(HttpSession session);

    void isLoginCheck(HttpServletRequest request);
}
