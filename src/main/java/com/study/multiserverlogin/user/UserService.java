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

    public void userSave(UserValue userValue) {
        userEntityRepository.save(UserEntity.create(userValue));
    }

    /**
     * - 성공 시 response header 에 loginSession : sessionKey 를 넣어서 응답.
     */
    public ResponseEntity<BasicResponse> login(UserValue userValue, HttpSession session) {

        Boolean userCheck = userEntityRepository.existsByUserIdAndPassword(userValue.getUserId(), userValue.getPassword());

        if (!userCheck) {
            return ResponseEntity
                    .badRequest()
                    .body(BasicResponse.createResponse(
                            "아이디 또는 비밀번호를 확인해 주세요.",
                            userValue
                    ));
        }

        String sessionKey = createSession(userValue.getUserId());
        session.setAttribute("Login_session", sessionKey);

        return ResponseEntity
                .ok()
                .body(BasicResponse.createResponse(
                        "로그인 성공",
                        null
                ));
    }

    private String createSession(String userId) {
        UserEntity userEntity = userEntityRepository.findByUserId(userId);
        String sessionKey = UUID.randomUUID().toString();
        loginSessionRepository.save(
                LoginSession.create(sessionKey, userEntity.getId())
        );
        return sessionKey;
    }


}
