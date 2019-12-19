package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/25.
 */

public class LookingLaborBean {

    /**
     * code : 1
     * message :
     * data : [{"company":"","start_date":1,"end_date":1,"idle_num":1,"user_name":"","mobile":"","idle_item_id":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * company :
         * start_date : 1
         * end_date : 1
         * idle_num : 1
         * user_name :
         * mobile :
         * idle_item_id : 1
         */

        private String company;
        private int start_date;
        private int end_date;
        private int idle_num;
        private String user_name;
        private String mobile;
        private int idle_item_id;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getStart_date() {
            return start_date;
        }

        public void setStart_date(int start_date) {
            this.start_date = start_date;
        }

        public int getEnd_date() {
            return end_date;
        }

        public void setEnd_date(int end_date) {
            this.end_date = end_date;
        }

        public int getIdle_num() {
            return idle_num;
        }

        public void setIdle_num(int idle_num) {
            this.idle_num = idle_num;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIdle_item_id() {
            return idle_item_id;
        }

        public void setIdle_item_id(int idle_item_id) {
            this.idle_item_id = idle_item_id;
        }
    }
}
