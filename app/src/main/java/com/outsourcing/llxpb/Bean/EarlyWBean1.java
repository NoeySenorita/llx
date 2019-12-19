package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWBean1 {

    /**
     * code : 0
     * message :
     * data : [{"verify_date":"","not_match_migrant_worker_num":1,"not_match_migrant_worker_list":[{"name":"","id":1}],"verify_migrant_worker_num":1}]
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
         * verify_date :
         * not_match_migrant_worker_num : 1
         * not_match_migrant_worker_list : [{"name":"","id":1}]
         * verify_migrant_worker_num : 1
         */

        private String verify_date;
        private int not_match_migrant_worker_num;
        private int verify_migrant_worker_num;
        private List<NotMatchMigrantWorkerListBean> not_match_migrant_worker_list;

        public String getVerify_date() {
            return verify_date;
        }

        public void setVerify_date(String verify_date) {
            this.verify_date = verify_date;
        }

        public int getNot_match_migrant_worker_num() {
            return not_match_migrant_worker_num;
        }

        public void setNot_match_migrant_worker_num(int not_match_migrant_worker_num) {
            this.not_match_migrant_worker_num = not_match_migrant_worker_num;
        }

        public int getVerify_migrant_worker_num() {
            return verify_migrant_worker_num;
        }

        public void setVerify_migrant_worker_num(int verify_migrant_worker_num) {
            this.verify_migrant_worker_num = verify_migrant_worker_num;
        }

        public List<NotMatchMigrantWorkerListBean> getNot_match_migrant_worker_list() {
            return not_match_migrant_worker_list;
        }

        public void setNot_match_migrant_worker_list(List<NotMatchMigrantWorkerListBean> not_match_migrant_worker_list) {
            this.not_match_migrant_worker_list = not_match_migrant_worker_list;
        }

        public static class NotMatchMigrantWorkerListBean {
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
