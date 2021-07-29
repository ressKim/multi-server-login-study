package com.study.multiserverlogin.user;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.NonFinal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

//@Value(staticConstructor = "create")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserValue {

    @NotBlank
    String userId;
    @NotBlank
    String password;

}
