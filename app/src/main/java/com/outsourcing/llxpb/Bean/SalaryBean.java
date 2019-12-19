package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/10/9.
 */

public class SalaryBean {

    /**
     * code :
     * message :
     * data : {"wage_apply_amount":1,"wage_pay_amount":1,"next_apply_date":1,"next_apply_counted_down":1}
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
         * wage_apply_amount : 1
         * wage_pay_amount : 1
         * next_apply_date : 1
         * next_apply_counted_down : 1
         */

        private float wage_apply_amount;
        private float wage_pay_amount;
        private int next_apply_date;
        private int next_apply_counted_down;

        public float getWage_apply_amount() {
            return wage_apply_amount;
        }

        public void setWage_apply_amount(float wage_apply_amount) {
            this.wage_apply_amount = wage_apply_amount;
        }

        public float getWage_pay_amount() {
            return wage_pay_amount;
        }

        public void setWage_pay_amount(float wage_pay_amount) {
            this.wage_pay_amount = wage_pay_amount;
        }

        public int getNext_apply_date() {
            return next_apply_date;
        }

        public void setNext_apply_date(int next_apply_date) {
            this.next_apply_date = next_apply_date;
        }

        public int getNext_apply_counted_down() {
            return next_apply_counted_down;
        }

        public void setNext_apply_counted_down(int next_apply_counted_down) {
            this.next_apply_counted_down = next_apply_counted_down;
        }
    }
}
