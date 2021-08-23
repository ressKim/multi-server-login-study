package com.study.multiserverlogin.login;

import com.study.multiserverlogin.response.login.LoginResponse;
import com.study.multiserverlogin.user.UserValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface LoginService {

    boolean login(UserValue userValue, HttpServletResponse response);

//    LoginResponse loginCheck(HttpSession session);

    boolean isLoginCheck(HttpSession session);
}
