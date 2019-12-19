package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/15.
 */

public class MonthlyCompletionDeclarationMessageBean {

    /**
     * code : 0
     * message : success
     * data : {"wage_apply_pay_list":[{"month":201908,"apply_wage":1,"pay_amount":1,"pay_list":[{"id":1,"pay_day":1,"pay_wage":1}]}],"wage_apply_summary":{"apply_total":1,"pay_total":1},"migrant_worker_id":1,"migrant_worker_name":""}
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
         * wage_apply_pay_list : [{"month":201908,"apply_wage":1,"pay_amount":1,"pay_list":[{"id":1,"pay_day":1,"pay_wage":1}]}]
         * wage_apply_summary : {"apply_total":1,"pay_total":1}
         * migrant_worker_id : 1
         * migrant_worker_name :
         */

        private WageApplySummaryBean wage_apply_summary;
        private int migrant_worker_id;
        private String migrant_worker_name;
        private List<WageApplyPayListBean> wage_apply_pay_list;

        public WageApplySummaryBean getWage_apply_summary() {
            return wage_apply_summary;
        }

        public void setWage_apply_summary(WageApplySummaryBean wage_apply_summary) {
            this.wage_apply_summary = wage_apply_summary;
        }

        public int getMigrant_worker_id() {
            return migrant_worker_id;
        }

        public void setMigrant_worker_id(int migrant_worker_id) {
            this.migrant_worker_id = migrant_worker_id;
        }

        public String getMigrant_worker_name() {
            return migrant_worker_name;
        }

        public void setMigrant_worker_name(String migrant_worker_name) {
            this.migrant_worker_name = migrant_worker_name;
        }

        public List<WageApplyPayListBean> getWage_apply_pay_list() {
            return wage_apply_pay_list;
        }

        public void setWage_apply_pay_list(List<WageApplyPayListBean> wage_apply_pay_list) {
            this.wage_apply_pay_list = wage_apply_pay_list;
        }

        public static class WageApplySummaryBean {
            /**
             * apply_total : 1
             * pay_total : 1
             */

            private int apply_total;
            private int pay_total;

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
        }

        public static class WageApplyPayListBean {
            /**
             * month : 201908
             * apply_wage : 1
             * pay_amount : 1
             * pay_list : [{"id":1,"pay_day":1,"pay_wage":1}]
             */

            private int month;
            private int apply_wage;
            private int pay_amount;
            private List<PayListBean> pay_list;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getApply_wage() {
                return apply_wage;
            }

            public void setApply_wage(int apply_wage) {
                this.apply_wage = apply_wage;
            }

            public int getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(int pay_amount) {
                this.pay_amount = pay_amount;
            }

            public List<PayListBean> getPay_list() {
                return pay_list;
            }

            public void setPay_list(List<PayListBean> pay_list) {
                this.pay_list = pay_list;
            }

            public static class PayListBean {
                /**
                 * id : 1
                 * pay_day : 1
                 * pay_wage : 1
                 */

                private int id;
                private int pay_day;
                private int pay_wage;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPay_day() {
                    return pay_day;
                }

                public void setPay_day(int pay_day) {
                    this.pay_day = pay_day;
                }

                public int getPay_wage() {
                    return pay_wage;
                }

                public void setPay_wage(int pay_wage) {
                    this.pay_wage = pay_wage;
                }
            }
        }
    }
}
