package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/11/19.
 */

public class RecognizeBankCardBean {

    /**
     * code : 0
     * message : success
     * data : {"bank_card_number":""}
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
         * bank_card_number :
         */

        private String bank_card_number;

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }
    }
}
