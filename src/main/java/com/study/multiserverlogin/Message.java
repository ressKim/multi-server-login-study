package com.study.multiserverlogin;

/**
 * response 되는 임시 message
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
