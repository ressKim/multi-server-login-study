package com.study.multiserverlogin.user;

import com.study.multiserverlogin.login.LoginService;
import com.study.multiserverlogin.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    /**
     * @param userValue user 회원가입 - 지금은 중복된 userId 상관없이 가입
     */
    @PostMapping("/join")
    public ResponseEntity<BasicResponse> saveUser(@RequestBody UserValue userValue) {

        if (UserValue.validCheck(userValue)) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            BasicResponse.createResponse("아이디 또는 비밀번호를 확인해 주세요", userValue)
                    );
        }

        userService.userSave(userValue);

        return ResponseEntity
                .ok()
                .body(
                        BasicResponse.createResponse("회원가입 성공", userValue)
                );
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> login(@RequestBody UserValue userValue, HttpSession session) {
        return loginService.login(userValue, session);
    }

}
