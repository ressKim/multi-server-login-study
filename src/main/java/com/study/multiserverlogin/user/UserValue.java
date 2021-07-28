package com.study.multiserverlogin.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

//@Value
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserValue {

    //user 처리

    @NotBlank
    String userId;
    @NotBlank
    String password;

    public static UserValue createUserValue(String userId, String password) {
        return new UserValue(userId, password);
    }

//    public UserValue() {
//    }
}
