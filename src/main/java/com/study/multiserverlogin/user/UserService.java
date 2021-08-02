package com.study.multiserverlogin.user;

import com.study.multiserverlogin.domain.session.LoginSession;
import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final LoginSessionRepository loginSessionRepository;

    public ResponseEntity<BasicResponse> userSave(UserValue userValue) {
        //아이디 중복이면 badRequest return
        if (userEntityRepository.existsByUserId(userValue.getUserId())) {
            return
                    ResponseEntity
                            .badRequest()
                            .body(
                                    BasicResponse.create(
                                            "중복된 아이디입니다.",
                                            userValue
                                    ));
        }
        userEntityRepository.save(UserEntity.create(userValue));
        //성공시 성공 return
        return ResponseEntity.ok(BasicResponse.create("회원가입 성공", userValue));
    }


}
