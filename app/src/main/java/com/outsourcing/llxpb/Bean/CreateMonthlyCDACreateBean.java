package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/9/16.
 */

public class CreateMonthlyCDACreateBean {

    /**
     * code : 0
     * message : success
     * data : {}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
