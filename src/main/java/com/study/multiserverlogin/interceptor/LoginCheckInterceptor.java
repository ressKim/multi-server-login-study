package com.study.multiserverlogin.interceptor;

import com.study.multiserverlogin.error.exception.LoginCheckException;
import com.study.multiserverlogin.error.message.LoginCheckExceptionMessage;
import com.study.multiserverlogin.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("loginCheckInterceptor 실행 {}", request);

        HttpSession session = request.getSession();

        if (!loginService.isLoginCheck(session)) {
            throw LoginCheckException.create(LoginCheckExceptionMessage.NEED_LOGIN);
        }

        return true;
    }

}