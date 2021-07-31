package com.study.multiserverlogin.user;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.NonFinal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

//@Value(staticConstructor = "create")
@Getter
@ToString
public class UserValue {

    private String userId;
    private String password;


    //UserValue 검증
    //현재 null 만 검증
    public static boolean validCheck(UserValue userValue){
        return userValue.getUserId() == null || userValue.getPassword() == null;
    }
}
