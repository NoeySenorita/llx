package com.outsourcing.llxpb.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2019/9/16.
 */

public class CheckIDCareBean implements Serializable{

    /**
     * code : 0
     * message : success
     * data : {"no_bank_card_count":1,"no_bank_card_list":[{"id":1,"name":""}]}
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

    public static class DataBean implements Serializable{
        /**
         * no_bank_card_count : 1
         * no_bank_card_list : [{"id":1,"name":""}]
         */

        private int no_bank_card_count;
        private List<NoBankCardListBean> no_bank_card_list;

        public int getNo_bank_card_count() {
            return no_bank_card_count;
        }

        public void setNo_bank_card_count(int no_bank_card_count) {
            this.no_bank_card_count = no_bank_card_count;
        }

        public List<NoBankCardListBean> getNo_bank_card_list() {
            return no_bank_card_list;
        }

        public void setNo_bank_card_list(List<NoBankCardListBean> no_bank_card_list) {
            this.no_bank_card_list = no_bank_card_list;
        }

        public static class NoBankCardListBean implements Serializable{
            /**
             * id : 1
             * name :
             */

            private int id;
            private String name;

            public String getIdCare() {
                return idCare;
            }

            public void setIdCare(String idCare) {
                this.idCare = idCare;
            }

            private String idCare;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
