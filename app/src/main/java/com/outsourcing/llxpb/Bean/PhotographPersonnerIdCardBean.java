package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/7/12.
 */

public class PhotographPersonnerIdCardBean {

    /**
     * code : 0
     * msg : success
     * data : {"front_id_card":"","back_id_card":""}
     */

    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * front_id_card :
         * back_id_card :
         */

        private String front_id_card;
        private String back_id_card;

        public String getFront_id_card() {
            return front_id_card;
        }

        public void setFront_id_card(String front_id_card) {
            this.front_id_card = front_id_card;
        }

        public String getBack_id_card() {
            return back_id_card;
        }

        public void setBack_id_card(String back_id_card) {
            this.back_id_card = back_id_card;
        }
    }
}
