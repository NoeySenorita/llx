package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWBean2 {
    /**
     * code : 0
     * message :
     * data : [{"month":1,"over_age_num":1,"over_age_percent":1,"over_age_list":[{"name":"","id":1}],"in_project_num":1}]
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
         * month : 1
         * over_age_num : 1
         * over_age_percent : 1
         * over_age_list : [{"name":"","id":1}]
         * in_project_num : 1
         */

        private int month;
        private int over_age_num;
        private int over_age_percent;
        private int in_project_num;
        private List<OverAgeListBean> over_age_list;

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getOver_age_num() {
            return over_age_num;
        }

        public void setOver_age_num(int over_age_num) {
            this.over_age_num = over_age_num;
        }

        public int getOver_age_percent() {
            return over_age_percent;
        }

        public void setOver_age_percent(int over_age_percent) {
            this.over_age_percent = over_age_percent;
        }

        public int getIn_project_num() {
            return in_project_num;
        }

        public void setIn_project_num(int in_project_num) {
            this.in_project_num = in_project_num;
        }

        public List<OverAgeListBean> getOver_age_list() {
            return over_age_list;
        }

        public void setOver_age_list(List<OverAgeListBean> over_age_list) {
            this.over_age_list = over_age_list;
        }

        public static class OverAgeListBean {
            /**
             * name :
             * id : 1
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
