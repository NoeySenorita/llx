package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/7/27.
 */

public class CheckIdCardBean {

    /**
     * code : 0
     * message : success
     * data : {"id_card_verify_flag":1,"id_card_verify_msg":""}
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
         * id_card_verify_flag : 1
         * id_card_verify_msg :
         */

        private int id_card_verify_flag;
        private String id_card_verify_msg;

        public int getId_card_verify_flag() {
            return id_card_verify_flag;
        }

        public void setId_card_verify_flag(int id_card_verify_flag) {
            this.id_card_verify_flag = id_card_verify_flag;
        }

        public String getId_card_verify_msg() {
            return id_card_verify_msg;
        }

        public void setId_card_verify_msg(String id_card_verify_msg) {
            this.id_card_verify_msg = id_card_verify_msg;
        }
    }
}
