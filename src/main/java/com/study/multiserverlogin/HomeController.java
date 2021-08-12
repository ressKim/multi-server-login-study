package com.study.multiserverlogin;

import com.study.multiserverlogin.response.BaseResponse;
import com.study.multiserverlogin.login.LoginServiceImpl;
import com.study.multiserverlogin.response.login.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final LoginServiceImpl loginService;

    /*
     * 현재 프로젝트에서 로그인/비로그인 구분 하는 곳
     */
    @GetMapping("/")
    public String homeTest() {
        return "ok";
    }

    @GetMapping("/login-check")
    public ResponseEntity<? extends BaseResponse> loginCheck(HttpSession session) {
        return ResponseEntity.ok(LoginResponse.success("로그인 중", null));
    }

}
