package com.study.multiserverlogin.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.multiserverlogin.domain.session.SessionName;
import com.study.multiserverlogin.error.exception.LoginCheckException;
import com.study.multiserverlogin.login.LoginService;
import com.study.multiserverlogin.response.login.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("loginCheckInterceptor 실행 {}", request);

        HttpSession session = request.getSession();

//        if (session == null || session.getAttribute(SessionName.LOGIN_SESSION.name()) == null) {

//            LoginResponse<String> resultMessage = LoginResponse.needLogin("로그인이 필요합니다.!!", requestURI, "");
//            String resultStringMessage = objectMapper.writeValueAsString(resultMessage);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().write(resultStringMessage);
//
//            return false;

        if (!loginService.isLoginCheck(session)) {
            throw new LoginCheckException("로그인 되지 않은 사용자.");
        }

        return true;
    }

}