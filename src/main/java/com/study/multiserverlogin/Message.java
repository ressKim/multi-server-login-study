package com.study.multiserverlogin;

/**
 * 현재 사용되지 않는 message
 * BasicResponse 로 대체
 */
public class Message {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
    }
}
