package com.study.multiserverlogin;

import com.study.multiserverlogin.response.BasicResponse;
import com.study.multiserverlogin.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final LoginService loginService;

    /**
     * 현재 프로젝트에서 로그인/비로그인 구분 하는 곳
     *
     */
    @GetMapping("/")
    public ResponseEntity<BasicResponse> homeTest(HttpSession session){
        return loginService.loginCheck(session);
    }

}
