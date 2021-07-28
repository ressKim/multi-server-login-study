package com.study.multiserverlogin.domain.user;

import com.study.multiserverlogin.user.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

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

    public static UserEntity createUser(UserDto userDto) {
        return new UserEntity(null, userDto.getUserId(), userDto.getPassword());
    }

//    public UserEntity() {
//
//    }
}