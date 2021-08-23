package com.study.multiserverlogin.login;

import com.study.multiserverlogin.domain.session.LoginSession;
import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.session.SessionConst;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.error.exception.LoginCheckException;
import com.study.multiserverlogin.error.message.LoginCheckExceptionMessage;
import com.study.multiserverlogin.user.UserValue;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

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

    /**
     * login cookie 에서 문제가 있다면 exception 으로 던지고
     * 문제가 없다면 세션 시간을 갱신하고 넘어갑니다.
     */
    @Override
    public void isLoginCheck(HttpServletRequest request) {

        Cookie getLoginCookie = getCookie(request);

        String sessionValue = getLoginCookie.getValue();

        LoginSession loginSession = sessionTimeCheck(sessionValue);
        //세션 시간 현재로 갱신
        loginSession.updateSessionTime();
        loginSessionRepository.save(loginSession);

        //로그인 중
    }


    /**
     * cookie 에서 로그인 관련 쿠키 들고오기
     */

    private Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie getLoginCookie = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(SessionConst.LOGIN_USER)) {
                getLoginCookie = cookie;
            }
        }
        // login 관련 cookie 가 아예 없으면 NEED_LOGIN return
        if (getLoginCookie == null) {
            throw LoginCheckException.create(LoginCheckExceptionMessage.NEED_LOGIN);
        }
        return getLoginCookie;
    }

    /**
     * cookie 의 시간 DB 에서 체크
     */
    private LoginSession sessionTimeCheck(String sessionValue) {
        LoginSession loginSession = loginSessionRepository.findBySessionKey(sessionValue);
        long sessionInterval = Duration.between(loginSession.getSessionTime(), LocalDateTime.now()).getSeconds();

        if (sessionInterval > LOGIN_SESSION_TIME) {
            loginSessionRepository.deleteById(loginSession.getId());
            //세션 만료
            throw LoginCheckException.create(LoginCheckExceptionMessage.SESSION_EXPIRATION);
        }
        return loginSession;
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
