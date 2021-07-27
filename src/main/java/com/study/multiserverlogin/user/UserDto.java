package com.study.multiserverlogin.user;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {

    //user 처리

    @NotBlank
    private String userId;
    @NotBlank
    private String password;

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserDto() {
    }
}
