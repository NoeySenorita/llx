package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/25.
 */

public class IReleaseMyBean {


    /**
     * code : 1
     * message :
     * data : [{"id":"","release_time":1,"idle_list":[{"work_type_name":"","idle_num":1,"start_date":1,"end_date":1,"is_valid":1}]}]
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
         * id :
         * release_time : 1
         * idle_list : [{"work_type_name":"","idle_num":1,"start_date":1,"end_date":1,"is_valid":1}]
         */

        private String id;
        private String  release_time;
        private List<IdleListBean> idle_list;
        private int is_valid;
        public int getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(int is_valid) {
            this.is_valid = is_valid;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String  getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }

        public List<IdleListBean> getIdle_list() {
            return idle_list;
        }

        public void setIdle_list(List<IdleListBean> idle_list) {
            this.idle_list = idle_list;
        }

        public static class IdleListBean {
            /**
             * work_type_name :
             * idle_num : 1
             * start_date : 1
             * end_date : 1
             * is_valid : 1
             */

            private String work_type_name;
            private int idle_num;
            private int start_date;
            private int end_date;


            public String getWork_type_name() {
                return work_type_name;
            }

            public void setWork_type_name(String work_type_name) {
                this.work_type_name = work_type_name;
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


        }
    }
}
