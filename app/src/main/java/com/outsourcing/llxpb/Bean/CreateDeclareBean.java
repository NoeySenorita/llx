package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/8/23.
 */

public class CreateDeclareBean {


    /**
     * code : 0
     * message : success
     * data : {"apply_month":201908,"contract_list":[{"id":24,"code":"CHHKH02","name":"黄黄高铁-康华合同02","show_name":"黄黄高铁-康华合同02","item_list":[{"item_id":360,"item_name":"路堤脚墙混凝土","item_price":130,"suggest_construction_position":"桥墩1"},{"item_id":361,"item_name":"路堑边坡截水骨架护坡、踏步、拦水坎混凝土","item_price":330,"suggest_construction_position":"桥墩1"},{"item_id":362,"item_name":"混凝土挡土墙","item_price":100,"suggest_construction_position":"桥洞2"},{"item_id":363,"item_name":"盲沟、渗沟混凝土","item_price":90,"suggest_construction_position":""},{"item_id":364,"item_name":"浆砌利用石增运0.5km","item_price":0.9,"suggest_construction_position":""}],"prior_period":"2019-08-21","apply_times":0,"applied_amount":0},{"id":25,"code":"CHHKH03","name":"黄黄高铁-康华合同03","show_name":"黄黄高铁-康华合同03","item_list":[{"item_id":365,"item_name":"路堤排水沟混凝土","item_price":240,"suggest_construction_position":"山洞1"},{"item_id":366,"item_name":"盲沟、渗沟混凝土","item_price":90,"suggest_construction_position":"桥墩1"}],"prior_period":"2019-08-21","apply_times":0,"applied_amount":0}]}
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
         * contract_list : [{"id":24,"code":"CHHKH02","name":"黄黄高铁-康华合同02","show_name":"黄黄高铁-康华合同02","item_list":[{"item_id":360,"item_name":"路堤脚墙混凝土","item_price":130,"suggest_construction_position":"桥墩1"},{"item_id":361,"item_name":"路堑边坡截水骨架护坡、踏步、拦水坎混凝土","item_price":330,"suggest_construction_position":"桥墩1"},{"item_id":362,"item_name":"混凝土挡土墙","item_price":100,"suggest_construction_position":"桥洞2"},{"item_id":363,"item_name":"盲沟、渗沟混凝土","item_price":90,"suggest_construction_position":""},{"item_id":364,"item_name":"浆砌利用石增运0.5km","item_price":0.9,"suggest_construction_position":""}],"prior_period":"2019-08-21","apply_times":0,"applied_amount":0},{"id":25,"code":"CHHKH03","name":"黄黄高铁-康华合同03","show_name":"黄黄高铁-康华合同03","item_list":[{"item_id":365,"item_name":"路堤排水沟混凝土","item_price":240,"suggest_construction_position":"山洞1"},{"item_id":366,"item_name":"盲沟、渗沟混凝土","item_price":90,"suggest_construction_position":"桥墩1"}],"prior_period":"2019-08-21","apply_times":0,"applied_amount":0}]
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
             * id : 24
             * code : CHHKH02
             * name : 黄黄高铁-康华合同02
             * show_name : 黄黄高铁-康华合同02
             * item_list : [{"item_id":360,"item_name":"路堤脚墙混凝土","item_price":130,"suggest_construction_position":"桥墩1"},{"item_id":361,"item_name":"路堑边坡截水骨架护坡、踏步、拦水坎混凝土","item_price":330,"suggest_construction_position":"桥墩1"},{"item_id":362,"item_name":"混凝土挡土墙","item_price":100,"suggest_construction_position":"桥洞2"},{"item_id":363,"item_name":"盲沟、渗沟混凝土","item_price":90,"suggest_construction_position":""},{"item_id":364,"item_name":"浆砌利用石增运0.5km","item_price":0.9,"suggest_construction_position":""}]
             * prior_period : 2019-08-21
             * apply_times : 0
             * applied_amount : 0.0
             */

            private int id;
            private String code;
            private String name;
            private String show_name;
            private String prior_period;
            private int apply_times;
            private double applied_amount;
            private List<ItemListBean> item_list;
            public double getAll() {
                return all;
            }

            public void setAll(double all) {
                this.all = all;
            }

            private double all;
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

            public double getApplied_amount() {
                return applied_amount;
            }

            public void setApplied_amount(double applied_amount) {
                this.applied_amount = applied_amount;
            }

            public List<ItemListBean> getItem_list() {
                return item_list;
            }

            public void setItem_list(List<ItemListBean> item_list) {
                this.item_list = item_list;
            }

            public static class ItemListBean {
                /**
                 * item_id : 360
                 * item_name : 路堤脚墙混凝土
                 * item_price : 130.0
                 * suggest_construction_position : 桥墩1
                 */

                private int item_id;
                private String item_name;
                private double item_price;
                private String suggest_construction_position;


                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                private int num;
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

                public double getItem_price() {
                    return item_price;
                }

                public void setItem_price(double item_price) {
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
