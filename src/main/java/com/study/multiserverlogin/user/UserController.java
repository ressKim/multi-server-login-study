package com.study.multiserverlogin.user;

import com.study.multiserverlogin.Message;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

//    @GetMapping("")
//    public UserDto getUser(@RequestParam UserDto userDto){
//
//        return null;
//    }

    /**
     * @param userValue user 회원가입 - 지금은 중복된 userId 상관없이 가입
     */
    @PostMapping("/join")
    public ResponseEntity<BasicResponse> saveUser(@RequestBody @Validated UserValue userValue, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
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
        return userService.login(userValue, session);
    }

}
