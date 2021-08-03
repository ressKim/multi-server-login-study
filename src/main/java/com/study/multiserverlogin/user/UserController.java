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
     * 성공시 status SUCCESS, 실패시 FAIL
     */
    @PostMapping("/join")
    public ResponseEntity<? extends BaseResponse> saveUser(@RequestBody UserValue userValue) {

        UserResponse<UserValue> resultResponse;
        if (UserValue.validCheck(userValue)) {
            resultResponse = UserResponse.fail(
                    "아이디 또는 비밀번호를 확인해 주세요", userValue);
        } else if (!userService.userSave(userValue)) {
            resultResponse = UserResponse.fail(
                    "중복된 아이디입니다.", userValue);
        } else {
            resultResponse = UserResponse.success(
                    "성공", userValue);
        }
        return ResponseEntity.ok(resultResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponse> login(@RequestBody UserValue userValue, HttpSession session) {

        UserResponse<UserValue> resultResponse;

        if (!loginService.login(userValue, session)) {
            resultResponse = UserResponse.fail(
                    "아이디 또는 비밀번호를 확인해 주세요.", userValue);
        } else {
            resultResponse = UserResponse.success(
                    "로그인 성공", null);
        }
        return ResponseEntity.ok(resultResponse);
    }

}
