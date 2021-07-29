package com.study.multiserverlogin.domain.user;

import com.study.multiserverlogin.user.UserValue;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Value()
public class UserEntity {
    /**
     * @id - user table pk
     * @userId - user의 로그인 아이디
     * @password - 비밀번호
     */
    @Id
    @GeneratedValue
    Long id;

    String userId;

    String password;

    public static UserEntity create(UserValue userValue) {
        return new UserEntity(null, userValue.getUserId(), userValue.getPassword());
    }

    private UserEntity(Long id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
}