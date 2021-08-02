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

    //세션 시간 설정
    private static final int LOGIN_SESSION_TIME = 5 * 60;


    /**
     * - login
     * - 성공 시 response header 에 loginSession : sessionKey 를 넣어서 응답.
     */
    public ResponseEntity<BasicResponse> login(UserValue userValue, HttpSession session) {

        Boolean userCheck = userEntityRepository.existsByUserIdAndPassword(userValue.getUserId(), userValue.getPassword());

        if (!userCheck) {
            return ResponseEntity
                    .badRequest()
                    .body(BasicResponse.create(
                            "아이디 또는 비밀번호를 확인해 주세요.",
                            userValue
                    ));
        }

        createLoginSession(userValue, session);

        return ResponseEntity
                .ok()
                .body(BasicResponse.create(
                        "로그인 성공",
                        null
                ));
    }


    public BasicResponse loginCheck(HttpSession session) {
        String sessionValue = (String) session.getAttribute(String.valueOf(LOGIN_SESSION));
        if (sessionValue == null) {
            return
                    BasicResponse.create(
                            "로그인 되지 않은 사용자 입니다.",
                            null
                    );
        }

        LoginSession loginSession = loginSessionRepository.findBySessionKey(sessionValue);
        long sessionInterval = Duration.between(loginSession.getSessionTime(), LocalDateTime.now()).getSeconds();

        if (sessionInterval > LOGIN_SESSION_TIME) {
            loginSessionRepository.deleteById(loginSession.getId());
            return
                    BasicResponse.create(
                            "세션이 만료되었습니다 다시 로그인해주세요",
                            null
                    );
        }

        //세션 시간 현재로 갱신
        loginSession.updateSessionTime();
        Long loginUserId = loginSession.getUserId();

        return
                BasicResponse.create(
                        "로그인중인 사용자",
                        loginUserId
                );
    }

    private void createLoginSession(UserValue userValue, HttpSession session) {
        String sessionKey = createSession(userValue.getUserId());
        session.setAttribute(String.valueOf(LOGIN_SESSION), sessionKey);
        session.setMaxInactiveInterval(LOGIN_SESSION_TIME);
    }

    /**
     * session 생성 후 session DB에도 저장
     */
    private String createSession(String userId) {
        UserEntity userEntity = userEntityRepository.findByUserId(userId);
        String sessionKey = UUID.randomUUID().toString();
        loginSessionRepository.save(
                LoginSession.create(sessionKey, userEntity.getId())
        );
        return sessionKey;
    }
}
