package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/25.
 */

public class SelectUpDadtaTypeWorkBean {

    /**
     * code : 0
     * message :
     * data : {"user_name":"","mobile":"","idle_list":[{"work_type_id":1,"idle_num":2,"start_date":20190910,"end_date":20190915,"work_type_name":""}]}
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
         * user_name :
         * mobile :
         * idle_list : [{"work_type_id":1,"idle_num":2,"start_date":20190910,"end_date":20190915,"work_type_name":""}]
         */

        private String user_name;
        private String mobile;
        private List<IdleListBean> idle_list;

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

        public List<IdleListBean> getIdle_list() {
            return idle_list;
        }

        public void setIdle_list(List<IdleListBean> idle_list) {
            this.idle_list = idle_list;
        }

        public static class IdleListBean {
            /**
             * work_type_id : 1
             * idle_num : 2
             * start_date : 20190910
             * end_date : 20190915
             * work_type_name :
             */

            private int work_type_id;
            private int idle_num;
            private int start_date;
            private int end_date;
            private String work_type_name;

            public int getWork_type_id() {
                return work_type_id;
            }

            public void setWork_type_id(int work_type_id) {
                this.work_type_id = work_type_id;
            }

            public int getIdle_num() {
                return idle_num;
            }

            public void setIdle_num(int idle_num) {
                this.idle_num = idle_num;
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

            public String getWork_type_name() {
                return work_type_name;
            }

            public void setWork_type_name(String work_type_name) {
                this.work_type_name = work_type_name;
            }
        }
    }
}
