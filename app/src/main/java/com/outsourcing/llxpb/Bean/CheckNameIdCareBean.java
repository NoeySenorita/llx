package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/12/10.
 */

public class CheckNameIdCareBean {

    /**
     * code : 0
     * message : success
     * data : {"name":"冯江华","birthday":19850419,"sex":2,"nationality":"汉","address":"广东省江门市新会区崖门镇横水第五队12号","allow_migrant_worker_entry":0,"not_allow_reason":"该民工已经进场该项目,如果要再次进场,需要先让该民工从项目中退场!","recognize_flag":0,"verify_flag":-1,"id_card_number":"440782198504193928","native_place":"广东省江门市"}
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
         * name : 冯江华
         * birthday : 19850419
         * sex : 2
         * nationality : 汉
         * address : 广东省江门市新会区崖门镇横水第五队12号
         * allow_migrant_worker_entry : 0
         * not_allow_reason : 该民工已经进场该项目,如果要再次进场,需要先让该民工从项目中退场!
         * recognize_flag : 0
         * verify_flag : -1
         * id_card_number : 440782198504193928
         * native_place : 广东省江门市
         */

        private String name;
        private int birthday;
        private int sex;
        private String nationality;
        private String address;
        private int allow_migrant_worker_entry;
        private String not_allow_reason;
        private int recognize_flag;
        private int verify_flag;
        private String id_card_number;

        public String getId_card_verify_msg() {
            return id_card_verify_msg;
        }

        public void setId_card_verify_msg(String id_card_verify_msg) {
            this.id_card_verify_msg = id_card_verify_msg;
        }

        private String id_card_verify_msg;
        private String native_place;

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

        public int getAllow_migrant_worker_entry() {
            return allow_migrant_worker_entry;
        }

        public void setAllow_migrant_worker_entry(int allow_migrant_worker_entry) {
            this.allow_migrant_worker_entry = allow_migrant_worker_entry;
        }

        public String getNot_allow_reason() {
            return not_allow_reason;
        }

        public void setNot_allow_reason(String not_allow_reason) {
            this.not_allow_reason = not_allow_reason;
        }

        public int getRecognize_flag() {
            return recognize_flag;
        }

        public void setRecognize_flag(int recognize_flag) {
            this.recognize_flag = recognize_flag;
        }

        public int getVerify_flag() {
            return verify_flag;
        }

        public void setVerify_flag(int verify_flag) {
            this.verify_flag = verify_flag;
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
    }
}
