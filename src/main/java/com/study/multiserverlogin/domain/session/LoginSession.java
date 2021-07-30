package com.study.multiserverlogin.domain.session;

import com.study.multiserverlogin.user.LoginValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
//@Value()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginSession {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;

    private String sessionKey;

    private LocalDateTime sessionTime;

    public static LoginSession create(String sessionKey, Long userId) {
        return new LoginSession(null, userId, sessionKey, LocalDateTime.now());
    }

    private LoginSession(Long id, Long userId, String sessionKey, LocalDateTime sessionTime) {
        this.id = id;
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.sessionTime = sessionTime;
    }
}
