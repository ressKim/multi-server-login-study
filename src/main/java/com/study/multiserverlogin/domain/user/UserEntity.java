package com.study.multiserverlogin.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
//현재 외부에서 사용할 일이 없으니깐 기본 생성자는 protected
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserEntity {
    /**
     * @id - user table pk
     * @userId - user의 로그인 아이디
     * @password - 비밀번호
     */
    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String password;


    public UserEntity(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}
