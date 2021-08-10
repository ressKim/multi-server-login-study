package com.study.multiserverlogin.config;

import com.study.multiserverlogin.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                //나중에 pattern 수정 필요
                .addPathPatterns("/login-check")
                //나중에 pattern 수정 필요
                .excludePathPatterns("/", "/login", "logout");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
