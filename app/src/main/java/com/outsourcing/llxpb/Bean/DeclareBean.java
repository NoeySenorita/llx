package com.outsourcing.llxpb.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2019/8/23.
 */

public class DeclareBean implements Serializable{

    /**
     * code : 0
     * message : success
     * data : [{"id":1,"matter_subject":"","matter_content":"","apply_month":201908,"matter_num":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * matter_subject :
         * matter_content :
         * apply_month : 201908
         * matter_num : 1
         */

        private int id;
        private String matter_subject;
        private String matter_content;
        private int apply_month;
        private int matter_num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMatter_subject() {
            return matter_subject;
        }

        public void setMatter_subject(String matter_subject) {
            this.matter_subject = matter_subject;
        }

        public String getMatter_content() {
            return matter_content;
        }

        public void setMatter_content(String matter_content) {
            this.matter_content = matter_content;
        }

        public int getApply_month() {
            return apply_month;
        }

        public void setApply_month(int apply_month) {
            this.apply_month = apply_month;
        }

        public int getMatter_num() {
            return matter_num;
        }

        public void setMatter_num(int matter_num) {
            this.matter_num = matter_num;
        }
    }
}
