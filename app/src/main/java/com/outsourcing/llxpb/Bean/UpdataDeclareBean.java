package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/10.
 */

public class UpdataDeclareBean {

    /**
     * code : 0
     * message : success
     * data : {"contract_list":[{"id":1,"code":"","name":"","item_list":[{"item_id":1,"item_name":"","item_price":1,"suggest_construction_position":""}],"prior_period":"","apply_times":1,"applied_amount":1,"show_name":""}],"apply_month":201908}
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
         * contract_list : [{"id":1,"code":"","name":"","item_list":[{"item_id":1,"item_name":"","item_price":1,"suggest_construction_position":""}],"prior_period":"","apply_times":1,"applied_amount":1,"show_name":""}]
         * apply_month : 201908
         */

        private int apply_month;
        private List<ContractListBean> contract_list;

        public int getApply_month() {
            return apply_month;
        }

        public void setApply_month(int apply_month) {
            this.apply_month = apply_month;
        }

        public List<ContractListBean> getContract_list() {
            return contract_list;
        }

        public void setContract_list(List<ContractListBean> contract_list) {
            this.contract_list = contract_list;
        }

        public static class ContractListBean {
            /**
             * id : 1
             * code :
             * name :
             * item_list : [{"item_id":1,"item_name":"","item_price":1,"suggest_construction_position":""}]
             * prior_period :
             * apply_times : 1
             * applied_amount : 1
             * show_name :
             */

            private int id;
            private String code;
            private String name;
            private String prior_period;
            private int apply_times;
            private int applied_amount;
            private String show_name;
            private List<ItemListBean> item_list;

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

            public String getPrior_period() {
                return prior_period;
            }

            public void setPrior_period(String prior_period) {
                this.prior_period = prior_period;
            }

            public int getApply_times() {
                return apply_times;
            }

            public void setApply_times(int apply_times) {
                this.apply_times = apply_times;
            }

            public int getApplied_amount() {
                return applied_amount;
            }

            public void setApplied_amount(int applied_amount) {
                this.applied_amount = applied_amount;
            }

            public String getShow_name() {
                return show_name;
            }

            public void setShow_name(String show_name) {
                this.show_name = show_name;
            }

            public List<ItemListBean> getItem_list() {
                return item_list;
            }

            public void setItem_list(List<ItemListBean> item_list) {
                this.item_list = item_list;
            }

            public static class ItemListBean {
                /**
                 * item_id : 1
                 * item_name :
                 * item_price : 1
                 * suggest_construction_position :
                 */

                private int item_id;
                private String item_name;
                private int item_price;
                private String suggest_construction_position;

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public String getItem_name() {
                    return item_name;
                }

                public void setItem_name(String item_name) {
                    this.item_name = item_name;
                }

                public int getItem_price() {
                    return item_price;
                }

                public void setItem_price(int item_price) {
                    this.item_price = item_price;
                }

                public String getSuggest_construction_position() {
                    return suggest_construction_position;
                }

                public void setSuggest_construction_position(String suggest_construction_position) {
                    this.suggest_construction_position = suggest_construction_position;
                }
            }
        }
    }
}
