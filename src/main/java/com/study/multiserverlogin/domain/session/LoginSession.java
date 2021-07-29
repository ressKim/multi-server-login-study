package com.study.multiserverlogin.domain.session;

import com.study.multiserverlogin.user.LoginValue;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Value()
public class LoginSession {

    @Id
    @GeneratedValue
    Long id;
    Long userId;

    String sessionKey;

    LocalDateTime sessionTime;

    public static LoginSession create(LoginValue loginValue, Long userId) {
        return new LoginSession(null, userId, loginValue.getSessionKey(), LocalDateTime.now());
    }

    private LoginSession(Long id, Long userId, String sessionKey, LocalDateTime sessionTime) {
        this.id = id;
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.sessionTime = sessionTime;
    }
}
