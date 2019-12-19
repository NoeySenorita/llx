package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/10/9.
 */

public class InspectionWorkBean {

    /**
     * code :
     * message :
     * data : {"output_apply_amount":1,"output_accept_amount":1,"next_apply_date":1,"next_apply_counted_down":1}
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
         * output_apply_amount : 1
         * output_accept_amount : 1
         * next_apply_date : 1
         * next_apply_counted_down : 1
         */

        private float output_apply_amount;
        private float output_accept_amount;
        private int next_apply_date;
        private int next_apply_counted_down;

        public float getOutput_apply_amount() {
            return output_apply_amount;
        }

        public void setOutput_apply_amount(float output_apply_amount) {
            this.output_apply_amount = output_apply_amount;
        }

        public float getOutput_accept_amount() {
            return output_accept_amount;
        }

        public void setOutput_accept_amount(float output_accept_amount) {
            this.output_accept_amount = output_accept_amount;
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
