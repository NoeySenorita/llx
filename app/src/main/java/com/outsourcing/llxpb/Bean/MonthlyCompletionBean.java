package com.outsourcing.llxpb.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2019/9/15.
 */

public class MonthlyCompletionBean implements Serializable{

    /**
     * code : 0
     * message : success
     * data : {"migrant_worker_stat":[{"id":1,"name":"","apply_amount":1,"pay_amount":1,"unpaid_amount":1}],"migrant_worker_summary":{"apply_total":1,"pay_total":1,"unpaid_total":1}}
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

    public static class DataBean implements Serializable{
        /**
         * migrant_worker_stat : [{"id":1,"name":"","apply_amount":1,"pay_amount":1,"unpaid_amount":1}]
         * migrant_worker_summary : {"apply_total":1,"pay_total":1,"unpaid_total":1}
         */

        private MigrantWorkerSummaryBean migrant_worker_summary;
        private List<MigrantWorkerStatBean> migrant_worker_stat;

        public MigrantWorkerSummaryBean getMigrant_worker_summary() {
            return migrant_worker_summary;
        }

        public void setMigrant_worker_summary(MigrantWorkerSummaryBean migrant_worker_summary) {
            this.migrant_worker_summary = migrant_worker_summary;
        }

        public List<MigrantWorkerStatBean> getMigrant_worker_stat() {
            return migrant_worker_stat;
        }

        public void setMigrant_worker_stat(List<MigrantWorkerStatBean> migrant_worker_stat) {
            this.migrant_worker_stat = migrant_worker_stat;
        }

        public static class MigrantWorkerSummaryBean implements Serializable{
            /**
             * apply_total : 1
             * pay_total : 1
             * unpaid_total : 1
             */

            private int apply_total;
            private int pay_total;
            private int unpaid_total;

            public int getApply_total() {
                return apply_total;
            }

            public void setApply_total(int apply_total) {
                this.apply_total = apply_total;
            }

            public int getPay_total() {
                return pay_total;
            }

            public void setPay_total(int pay_total) {
                this.pay_total = pay_total;
            }

            public int getUnpaid_total() {
                return unpaid_total;
            }

            public void setUnpaid_total(int unpaid_total) {
                this.unpaid_total = unpaid_total;
            }
        }

        public static class MigrantWorkerStatBean implements Serializable{
            /**
             * id : 1
             * name :
             * apply_amount : 1
             * pay_amount : 1
             * unpaid_amount : 1
             */

            private int id;
            private String name;
            private int apply_amount;
            private int pay_amount;
            private int unpaid_amount;

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

            public int getApply_amount() {
                return apply_amount;
            }

            public void setApply_amount(int apply_amount) {
                this.apply_amount = apply_amount;
            }

            public int getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(int pay_amount) {
                this.pay_amount = pay_amount;
            }

            public int getUnpaid_amount() {
                return unpaid_amount;
            }

            public void setUnpaid_amount(int unpaid_amount) {
                this.unpaid_amount = unpaid_amount;
            }
        }
    }
}
