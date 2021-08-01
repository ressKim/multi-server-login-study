package com.study.multiserverlogin.response;

import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "create")
public class BasicResponse<T> {

    String message;

    T result;


}
