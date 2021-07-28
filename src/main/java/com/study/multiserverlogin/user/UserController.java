package com.study.multiserverlogin.user;

import com.study.multiserverlogin.Message;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public Message saveUser(@RequestBody @Validated UserValue userValue, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Message("아이디 또는 비밀번호를 확인해 주세요");
        }

        userService.userSave(userValue);

        return new Message("success");
    }

}
