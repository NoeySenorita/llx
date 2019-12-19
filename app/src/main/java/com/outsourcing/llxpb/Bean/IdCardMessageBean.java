package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/7/27.
 */

public class IdCardMessageBean {

    /**
     * code : 0
     * message : success
     * data : {"id_card_number":"","name":"","birthday":19901001,"sex":1,"native_place":"","nationality":"","address":""}
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
         * id_card_number :
         * name :
         * birthday : 19901001
         * sex : 1
         * native_place :
         * nationality :
         * address :
         */

        private String id_card_number;
        private String name;
        private int birthday;
        private int sex;
        private String native_place;
        private String nationality;
        private String address;

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
