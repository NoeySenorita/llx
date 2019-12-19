package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/12/10.
 */

public class CheckInProjectBean {

    /**
     * code : 0
     * message : success
     * data : {"city_id":1,"allow_execute_entry":1,"check_msg":""}
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
        /**
         * city_id : 1
         * allow_execute_entry : 1
         * check_msg :
         */

        private int city_id;
        private int allow_execute_entry;
        private String check_msg;

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getAllow_execute_entry() {
            return allow_execute_entry;
        }

        public void setAllow_execute_entry(int allow_execute_entry) {
            this.allow_execute_entry = allow_execute_entry;
        }

        public String getCheck_msg() {
            return check_msg;
        }

        public void setCheck_msg(String check_msg) {
            this.check_msg = check_msg;
        }
    }
}
