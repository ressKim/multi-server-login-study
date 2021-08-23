package com.study.multiserverlogin.login;

import com.study.multiserverlogin.domain.session.LoginSession;
import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.session.SessionConst;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.error.exception.LoginCheckException;
import com.study.multiserverlogin.error.exception.LoginException;
import com.study.multiserverlogin.error.message.LoginCheckExceptionMessage;
import com.study.multiserverlogin.user.UserValue;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.study.multiserverlogin.domain.session.SessionName.*;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginSessionRepository loginSessionRepository;
    private final UserEntityRepository userEntityRepository;

    //세션 시간 설정
    private static final int LOGIN_SESSION_TIME = 5 * 60;


    /**
     * - login
     * - 성공 시 response header 에 loginSession : sessionKey 를 넣어서 응답.
     *
     * @return
     */
    @Override
    public boolean login(UserValue userValue, HttpServletResponse response) {

        Boolean userCheck = userEntityRepository.existsByUserIdAndPassword(userValue.getUserId(), userValue.getPassword());

        if (!userCheck) {
          throw LoginCheckException.create(LoginCheckExceptionMessage.NOT_FOUND_USER);
        }

        createLoginCookie(userValue, response);

        return true;
    }

    @Override
    public boolean isLoginCheck(HttpSession session) {
        String sessionValue = (String) session.getAttribute(String.valueOf(LOGIN_SESSION));
        if (sessionValue == null) {
            // 로그인 되지 않은 사용자
            return false;
        }

        LoginSession loginSession = loginSessionRepository.findBySessionKey(sessionValue);
        long sessionInterval = Duration.between(loginSession.getSessionTime(), LocalDateTime.now()).getSeconds();

        if (sessionInterval > LOGIN_SESSION_TIME) {
            loginSessionRepository.deleteById(loginSession.getId());
            //세션 만료
            return false;
        }
        //세션 시간 현재로 갱신
        loginSession.updateSessionTime();
        Long loginUserId = loginSession.getUserId();

        //로그인 중
        return true;
    }


  /**
   * LOGIN_USER cookie 에 key 값을 넣고
   * HttpServletResponse 에 보낸다.
   */
    private void createLoginCookie(UserValue userValue, HttpServletResponse response) {
        String sessionKey = createSession(userValue.getUserId());
        response.addCookie(new Cookie(SessionConst.LOGIN_USER, sessionKey));
    }

    /**
     * session 생성 후 session DB 에 저장
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
