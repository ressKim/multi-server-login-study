package com.study.multiserverlogin.login;

import com.study.multiserverlogin.domain.session.LoginSession;
import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.session.SessionName;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.response.BasicResponse;
import com.study.multiserverlogin.user.UserValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.study.multiserverlogin.domain.session.SessionName.*;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {
    private final LoginSessionRepository loginSessionRepository;
    private final UserEntityRepository userEntityRepository;


    /**
     * - login
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
        session.setAttribute(String.valueOf(LOGIN_SESSION), sessionKey);

        return ResponseEntity
                .ok()
                .body(BasicResponse.createResponse(
                        "로그인 성공",
                        null
                ));
    }

    public ResponseEntity<BasicResponse> loginCheck(HttpSession session) {
        String sessionValue = (String) session.getAttribute(String.valueOf(LOGIN_SESSION));
        if (sessionValue == null) {
            return ResponseEntity
                    .ok()
                    .body(
                            BasicResponse.createResponse(
                                    "로그인 되지 않은 사용자 입니다.",
                                    null
                            ));
        }

        LoginSession loginSession = loginSessionRepository.findBySessionKey(sessionValue);
        Duration duration = Duration.between(loginSession.getSessionTime(), LocalDateTime.now());
        //5분이상 차이나면 세션 만료 보내기
        if (duration.getSeconds() > 60 * 60 * 5) {
            loginSessionRepository.deleteById(loginSession.getId());
            return ResponseEntity
                    .ok()
                    .body(
                            BasicResponse.createResponse(
                                    "세션이 만료되었습니다 다시 로그인해주세요",
                                    null
                            ));
        }
        Long loginUserId = loginSession.getUserId();

        //세션 시간 현재로 갱신
        loginSession.updateSessionTime();

        return ResponseEntity
                .ok()
                .body(
                        BasicResponse.createResponse(
                                "로그인중인 사용자",
                                loginUserId
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
