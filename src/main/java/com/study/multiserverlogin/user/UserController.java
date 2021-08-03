package com.study.multiserverlogin.user;

import com.study.multiserverlogin.login.LoginService;
import com.study.multiserverlogin.response.BaseResponse;
import com.study.multiserverlogin.response.LoginResponse;
import com.study.multiserverlogin.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
     * user 회원가입
     * 성공시 status 200 맞지 않을시 status 400
     * 성공시 true 실패시 boolean
     */
    @PostMapping("/join")
    public ResponseEntity<? extends BaseResponse> saveUser(@RequestBody UserValue userValue) {

        if (UserValue.validCheck(userValue)) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            UserResponse.fail("아이디 또는 비밀번호를 확인해 주세요", userValue)
                    );
        }


        if (!userService.userSave(userValue)) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            UserResponse.fail(
                                    "중복된 아이디입니다.",
                                    userValue
                            ));
        }

        return ResponseEntity.ok(UserResponse.success("성공", userValue));
    }

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponse> login(@RequestBody UserValue userValue, HttpSession session) {

        if (!loginService.login(userValue, session)) {
            return ResponseEntity
                    .badRequest()
                    .body(UserResponse.fail(
                            "아이디 또는 비밀번호를 확인해 주세요.",
                            userValue
                    ));
        }

        return ResponseEntity
                .ok()
                .body(UserResponse.success(
                        "로그인 성공",
                        null
                ));
    }

}
