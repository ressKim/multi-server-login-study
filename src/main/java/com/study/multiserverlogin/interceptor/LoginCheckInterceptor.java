package com.study.multiserverlogin.interceptor;

import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.session.SessionName;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.error.exception.LoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    //    private final UserEntityRepository userEntityRepository;
//    private final LoginSessionRepository loginSessionRepository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("loginCheckInterceptor 실행 {}", request);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionName.LOGIN_SESSION.name()) == null){
            throw new LoginException("로그인 되지 않은 사용자.");
        }

        return true;
    }

}