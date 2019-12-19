package com.outsourcing.llxpb.manager;

/**
 * Created by DELL on 2019/12/15.
 */

public class MessageEvent {
    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
