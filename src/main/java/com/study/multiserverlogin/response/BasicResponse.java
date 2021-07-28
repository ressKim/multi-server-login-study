package com.study.multiserverlogin.response;

import lombok.Getter;
import lombok.Value;

@Value
public class BasicResponse<T> {

    String message;

    T result;

    public static <T> BasicResponse<T> createResponse(String message, T result){
        return new BasicResponse<>(message, result);
    }


}
