package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/11/12.
 */

public class HelpMessageBean {

    /**
     * code : 0
     * message : success
     * data : 1,,,,;2,,,,
     */

    private int code;
    private String message;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
