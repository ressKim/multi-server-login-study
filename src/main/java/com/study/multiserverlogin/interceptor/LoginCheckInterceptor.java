package com.study.multiserverlogin.interceptor;

import com.study.multiserverlogin.domain.session.SessionName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("loginCheckInterceptor 실행 {}", request);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionName.LOGIN_SESSION.name()) == null) {
            log.info("세션 없음");
            /**
             * 에러 controller 구현 후 넣어 줄 것.
             */
//            response.sendRedirect();
            return false;
        }

        return true;
    }

}
