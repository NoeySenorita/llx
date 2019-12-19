package com.outsourcing.llxpb.Bean;

import java.io.Serializable;

/**
 * Created by DELL on 2019/8/6.
 */

public class PerfectInformationBean implements Serializable{

    /**
     * code : 0
     * message : success
     * data : {"id":1,"mobile":"","work_type_id":1,"protocol_wage":1,"bank_card_number":"","bank_card_img":"","name":"","sex":1,"birthday":1,"id_card_number":"","native_place":"","nationality":"","facial_photo":""}
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
         * id : 1
         * mobile :
         * work_type_id : 1
         * protocol_wage : 1
         * bank_card_number :
         * bank_card_img :
         * name :
         * sex : 1
         * birthday : 1
         * id_card_number :
         * native_place :
         * nationality :
         * facial_photo :
         */

        private int id;
        private String mobile;
        private int work_type_id;
        private int protocol_wage;
        private String bank_card_number;
        private String bank_card_img;
        private String name;
        private int sex;
        private int birthday;
        private String id_card_number;
        private String native_place;
        private String nationality;
        private String facial_photo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getWork_type_id() {
            return work_type_id;
        }

        public void setWork_type_id(int work_type_id) {
            this.work_type_id = work_type_id;
        }

        public int getProtocol_wage() {
            return protocol_wage;
        }

        public void setProtocol_wage(int protocol_wage) {
            this.protocol_wage = protocol_wage;
        }

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }

        public String getBank_card_img() {
            return bank_card_img;
        }

        public void setBank_card_img(String bank_card_img) {
            this.bank_card_img = bank_card_img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getNative_place() {
            return native_place;
        }

        public void setNative_place(String native_place) {
            this.native_place = native_place;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getFacial_photo() {
            return facial_photo;
        }

        public void setFacial_photo(String facial_photo) {
            this.facial_photo = facial_photo;
        }
    }
}
