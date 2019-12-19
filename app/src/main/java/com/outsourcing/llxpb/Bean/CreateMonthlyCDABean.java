package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/16.
 */

public class CreateMonthlyCDABean {

    /**
     * code : 0
     * message : success
     * data : {"apply_month":201908,"migrant_worker_list":[{"id":1,"name":"","work_type_id":1,"work_type_name":"","work_day":1,"suggest_apply_wage":1,"protocol_wage":1}]}
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
         * apply_month : 201908
         * migrant_worker_list : [{"id":1,"name":"","work_type_id":1,"work_type_name":"","work_day":1,"suggest_apply_wage":1,"protocol_wage":1}]
         */

        private int apply_month;
        private List<MigrantWorkerListBean> migrant_worker_list;

        public int getApply_month() {
            return apply_month;
        }

        public void setApply_month(int apply_month) {
            this.apply_month = apply_month;
        }

        public List<MigrantWorkerListBean> getMigrant_worker_list() {
            return migrant_worker_list;
        }

        public void setMigrant_worker_list(List<MigrantWorkerListBean> migrant_worker_list) {
            this.migrant_worker_list = migrant_worker_list;
        }

        public static class MigrantWorkerListBean {
            /**
             * id : 1
             * name :
             * work_type_id : 1
             * work_type_name :
             * work_day : 1
             * suggest_apply_wage : 1
             * protocol_wage : 1
             */

            private int id;
            private String name;
            private int work_type_id;
            private String work_type_name;
            private int work_day;
            private long suggest_apply_wage;
            private int protocol_wage;

            public boolean isState() {
                return state;
            }

            public void setState(boolean state) {
                this.state = state;
            }

            private  boolean state;
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

            public int getWork_type_id() {
                return work_type_id;
            }

            public void setWork_type_id(int work_type_id) {
                this.work_type_id = work_type_id;
            }

            public String getWork_type_name() {
                return work_type_name;
            }

            public void setWork_type_name(String work_type_name) {
                this.work_type_name = work_type_name;
            }

            public int getWork_day() {
                return work_day;
            }

            public void setWork_day(int work_day) {
                this.work_day = work_day;
            }

            public long getSuggest_apply_wage() {
                return suggest_apply_wage;
            }

            public void setSuggest_apply_wage(long suggest_apply_wage) {
                this.suggest_apply_wage = suggest_apply_wage;
            }

            public int getProtocol_wage() {
                return protocol_wage;
            }

            public void setProtocol_wage(int protocol_wage) {
                this.protocol_wage = protocol_wage;
            }
        }
    }
}
