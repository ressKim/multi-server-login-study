package com.study.multiserverlogin.config;

import com.study.multiserverlogin.interceptor.LoginCheckInterceptor;
import com.study.multiserverlogin.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final LoginService loginService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(loginService))
                .order(1)
                //나중에 pattern 수정 필요
                .addPathPatterns("/login-check")
                //나중에 pattern 수정 필요
                .excludePathPatterns("/", "/login", "logout");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
