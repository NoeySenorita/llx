package com.outsourcing.llxpb.Bean;

public class WorkerDetailResult {

    /**
     * code : 0
     * message : success
     * data : {"name":"冯江华","sex":2,"age":34,"nationality":"汉","address":"广东省江门市新会区崖镇横水第五队12号","facial_photo":"https://laolixin-b1.oss-cn-hangzhou.aliyuncs.com/data/upload/roster/1/a6a978d5-cd8c-4692-a8f1-8d5a8f30a3a9.jpg","work_type_name":"普工","id_card_number":"440782198504193928","id_card_front_img":"https://laolixin-b1.oss-cn-hangzhou.aliyuncs.com/data/upload/roster/5/389a531c-f0a4-40f7-afa9-ff8c62833cf4.jpg","current_company_id":5,"current_company_name":"武汉康华劳务分包有限公司","current_company_short_name":"武汉康华","bank_card_number":"6228480711598385211","wage_total":34049,"in_project_time":72.09085215277777,"entry_record_count":2,"last_entry_record_time":1565246297508,"exit_record_count":1,"last_exit_record_time":1565245454104}
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
         * sex : 2
         * age : 34
         * nationality : 汉
         * address : 广东省江门市新会区崖镇横水第五队12号
         * facial_photo : https://laolixin-b1.oss-cn-hangzhou.aliyuncs.com/data/upload/roster/1/a6a978d5-cd8c-4692-a8f1-8d5a8f30a3a9.jpg
             * work_type_name : 普工
         * id_card_number : 440782198504193928
         * id_card_front_img : https://laolixin-b1.oss-cn-hangzhou.aliyuncs.com/data/upload/roster/5/389a531c-f0a4-40f7-afa9-ff8c62833cf4.jpg
         * current_company_id : 5
         * current_company_name : 武汉康华劳务分包有限公司
         * current_company_short_name : 武汉康华
         * bank_card_number : 6228480711598385211
         * wage_total : 34049
         * in_project_time : 72.09085215277777
         * entry_record_count : 2
         * last_entry_record_time : 1565246297508
         * exit_record_count : 1
         * last_exit_record_time : 1565245454104
         */

        private String name;
        private int sex;
        private int age;
        private String nationality;
        private String address;
        private String facial_photo;
        private String work_type_name;
        private String id_card_number;
        private String id_card_front_img;
        private int current_company_id;
        private String current_company_name;
        private String current_company_short_name;
        private String bank_card_number;
        private int wage_total;
        private double in_project_time;
        private int entry_record_count;
        private long last_entry_record_time;
        private int exit_record_count;
        private long last_exit_record_time;

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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public String getFacial_photo() {
            return facial_photo;
        }

        public void setFacial_photo(String facial_photo) {
            this.facial_photo = facial_photo;
        }

        public String getWork_type_name() {
            return work_type_name;
        }

        public void setWork_type_name(String work_type_name) {
            this.work_type_name = work_type_name;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getId_card_front_img() {
            return id_card_front_img;
        }

        public void setId_card_front_img(String id_card_front_img) {
            this.id_card_front_img = id_card_front_img;
        }

        public int getCurrent_company_id() {
            return current_company_id;
        }

        public void setCurrent_company_id(int current_company_id) {
            this.current_company_id = current_company_id;
        }

        public String getCurrent_company_name() {
            return current_company_name;
        }

        public void setCurrent_company_name(String current_company_name) {
            this.current_company_name = current_company_name;
        }

        public String getCurrent_company_short_name() {
            return current_company_short_name;
        }

        public void setCurrent_company_short_name(String current_company_short_name) {
            this.current_company_short_name = current_company_short_name;
        }

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }

        public int getWage_total() {
            return wage_total;
        }

        public void setWage_total(int wage_total) {
            this.wage_total = wage_total;
        }

        public double getIn_project_time() {
            return in_project_time;
        }

        public void setIn_project_time(double in_project_time) {
            this.in_project_time = in_project_time;
        }

        public int getEntry_record_count() {
            return entry_record_count;
        }

        public void setEntry_record_count(int entry_record_count) {
            this.entry_record_count = entry_record_count;
        }

        public long getLast_entry_record_time() {
            return last_entry_record_time;
        }

        public void setLast_entry_record_time(long last_entry_record_time) {
            this.last_entry_record_time = last_entry_record_time;
        }

        public int getExit_record_count() {
            return exit_record_count;
        }

        public void setExit_record_count(int exit_record_count) {
            this.exit_record_count = exit_record_count;
        }

        public long getLast_exit_record_time() {
            return last_exit_record_time;
        }

        public void setLast_exit_record_time(long last_exit_record_time) {
            this.last_exit_record_time = last_exit_record_time;
        }
    }
}
