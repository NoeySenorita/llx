package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/8/14.
 */

public class WageDIstributionMessageBean {
    /**
     * code : 0
     * message : success
     * data : {"item_stat":[{"item_id":360,"item_name":"路堤脚墙混凝土","item_price":130,"apply_item_num":387,"apply_item_amount":50310},{"item_id":361,"item_name":"路堑边坡截水骨架护坡、踏步、拦水坎混凝土","item_price":330,"apply_item_num":324,"apply_item_amount":106920},{"item_id":362,"item_name":"混凝土挡土墙","item_price":100,"apply_item_num":388,"apply_item_amount":38800},{"item_id":363,"item_name":"盲沟、渗沟混凝土","item_price":90,"apply_item_num":230,"apply_item_amount":20700},{"item_id":364,"item_name":"浆砌利用石增运0.5km","item_price":0.9,"apply_item_num":260,"apply_item_amount":234}],"amount":216964}
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
         * item_stat : [{"item_id":360,"item_name":"路堤脚墙混凝土","item_price":130,"apply_item_num":387,"apply_item_amount":50310},{"item_id":361,"item_name":"路堑边坡截水骨架护坡、踏步、拦水坎混凝土","item_price":330,"apply_item_num":324,"apply_item_amount":106920},{"item_id":362,"item_name":"混凝土挡土墙","item_price":100,"apply_item_num":388,"apply_item_amount":38800},{"item_id":363,"item_name":"盲沟、渗沟混凝土","item_price":90,"apply_item_num":230,"apply_item_amount":20700},{"item_id":364,"item_name":"浆砌利用石增运0.5km","item_price":0.9,"apply_item_num":260,"apply_item_amount":234}]
         * amount : 216964.0
         */

        private double amount;
        private List<ItemStatBean> item_stat;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public List<ItemStatBean> getItem_stat() {
            return item_stat;
        }

        public void setItem_stat(List<ItemStatBean> item_stat) {
            this.item_stat = item_stat;
        }

        public static class ItemStatBean {
            /**
             * item_id : 360
             * item_name : 路堤脚墙混凝土
             * item_price : 130.0
             * apply_item_num : 387.0
             * apply_item_amount : 50310.0
             */

            private int item_id;
            private String item_name;
            private double item_price;
            private double apply_item_num;
            private double apply_item_amount;

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

            public double getApply_item_num() {
                return apply_item_num;
            }

            public void setApply_item_num(double apply_item_num) {
                this.apply_item_num = apply_item_num;
            }

            public double getApply_item_amount() {
                return apply_item_amount;
            }

            public void setApply_item_amount(double apply_item_amount) {
                this.apply_item_amount = apply_item_amount;
            }
        }
    }


    /**
     * code : 0
     * message : success
     * data : {"item_stat":[{"item_id":1,"item_name":"","item_price":1,"apply_item_num":1,"apply_item_amount":1}],"amount":1000}
     */


}
