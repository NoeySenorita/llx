package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/8/14.
 */

public class WageDistributionBean {
    /**
     * code : 0
     * message : success
     * data : {"contract_summary":{"accept_total":4,"apply_total":384604},"contract_stat":[{"id":24,"code":"CHHKH02","name":"黄黄高铁-康华合同02","show_name":"黄黄高铁-康华合同02","apply_amount":216964,"accept_amount":4},{"id":25,"code":"CHHKH03","name":"黄黄高铁-康华合同03","show_name":"黄黄高铁-康华合同03","apply_amount":167640,"accept_amount":0}],"matter_stat":{"matter_count":9}}
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
         * contract_summary : {"accept_total":4,"apply_total":384604}
         * contract_stat : [{"id":24,"code":"CHHKH02","name":"黄黄高铁-康华合同02","show_name":"黄黄高铁-康华合同02","apply_amount":216964,"accept_amount":4},{"id":25,"code":"CHHKH03","name":"黄黄高铁-康华合同03","show_name":"黄黄高铁-康华合同03","apply_amount":167640,"accept_amount":0}]
         * matter_stat : {"matter_count":9}
         */

        private ContractSummaryBean contract_summary;
        private MatterStatBean matter_stat;
        private List<ContractStatBean> contract_stat;

        public ContractSummaryBean getContract_summary() {
            return contract_summary;
        }

        public void setContract_summary(ContractSummaryBean contract_summary) {
            this.contract_summary = contract_summary;
        }

        public MatterStatBean getMatter_stat() {
            return matter_stat;
        }

        public void setMatter_stat(MatterStatBean matter_stat) {
            this.matter_stat = matter_stat;
        }

        public List<ContractStatBean> getContract_stat() {
            return contract_stat;
        }

        public void setContract_stat(List<ContractStatBean> contract_stat) {
            this.contract_stat = contract_stat;
        }

        public static class ContractSummaryBean {
            /**
             * accept_total : 4
             * apply_total : 384604
             */

            private int accept_total;
            private int apply_total;

            public int getAccept_total() {
                return accept_total;
            }

            public void setAccept_total(int accept_total) {
                this.accept_total = accept_total;
            }

            public int getApply_total() {
                return apply_total;
            }

            public void setApply_total(int apply_total) {
                this.apply_total = apply_total;
            }
        }

        public static class MatterStatBean {
            /**
             * matter_count : 9
             */

            private int matter_count;

            public int getMatter_count() {
                return matter_count;
            }

            public void setMatter_count(int matter_count) {
                this.matter_count = matter_count;
            }
        }

        public static class ContractStatBean {
            /**
             * id : 24
             * code : CHHKH02
             * name : 黄黄高铁-康华合同02
             * show_name : 黄黄高铁-康华合同02
             * apply_amount : 216964.0
             * accept_amount : 4.0
             */

            private int id;
            private String code;
            private String name;
            private String show_name;
            private String apply_amount;
            private String accept_amount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShow_name() {
                return show_name;
            }

            public void setShow_name(String show_name) {
                this.show_name = show_name;
            }

            public String getApply_amount() {
                return apply_amount;
            }

            public void setApply_amount(String apply_amount) {
                this.apply_amount = apply_amount;
            }

            public String getAccept_amount() {
                return accept_amount;
            }

            public void setAccept_amount(String accept_amount) {
                this.accept_amount = accept_amount;
            }
        }
    }

    /**
     * code : 0
     * message : success
     * data : {"contract_stat":[{"id":1,"code":"","apply_amount":1,"accept_amount":1,"name":""}],"matter_stat":{"matter_count":1}}
     * contract_summary : {"apply_total":1,"accept_total":1}
     */

    //{"code":0,"message":"success",
    // "data":{
    //      "contract_summary":
    //          {"accept_total":4,"apply_total":384604},
    //       "contract_stat":[{"id":24,"code":"CHHKH02","name":"黄黄高铁-康华合同02","show_name":"黄黄高铁-康华合同02","apply_amount":216964.0,"accept_amount":4.0},{"id":25,"code":"CHHKH03","name":"黄黄高铁-康华合同03","show_name":"黄黄高铁-康华合同03","apply_amount":167640.0,"accept_amount":0.0}],"matter_stat":{"matter_count":9}}}

}
