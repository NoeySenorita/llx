package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWBean3 {

    /**
     * code : 1
     * message :
     * data : [{"month":1,"local_person_num":1,"local_person_percent":1,"local_person_list":[{"id":1,"name":""}],"in_project_num":1}]
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
         * local_person_num : 1
         * local_person_percent : 1
         * local_person_list : [{"id":1,"name":""}]
         * in_project_num : 1
         */

        private int month;
        private double local_person_num;
        private double local_person_percent;
        private int in_project_num;
        private List<LocalPersonListBean> local_person_list;

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public double getLocal_person_num() {
            return local_person_num;
        }

        public void setLocal_person_num(double local_person_num) {
            this.local_person_num = local_person_num;
        }

        public double getLocal_person_percent() {
            return local_person_percent;
        }

        public void setLocal_person_percent(double local_person_percent) {
            this.local_person_percent = local_person_percent;
        }

        public int getIn_project_num() {
            return in_project_num;
        }

        public void setIn_project_num(int in_project_num) {
            this.in_project_num = in_project_num;
        }

        public List<LocalPersonListBean> getLocal_person_list() {
            return local_person_list;
        }

        public void setLocal_person_list(List<LocalPersonListBean> local_person_list) {
            this.local_person_list = local_person_list;
        }

        public static class LocalPersonListBean {
            /**
             * id : 1
             * name :
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
