package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/11/18.
 */

public class SubcontractorBean {

    /**
     * code : 1
     * message :
     * data : {"subcontractor_id":1,"subcontractor_name":"","subcontractor_short_name":""}
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
         * subcontractor_id : 1
         * subcontractor_name :
         * subcontractor_short_name :
         */

        private int subcontractor_id;
        private String subcontractor_name;
        private String subcontractor_short_name;

        public int getSubcontractor_id() {
            return subcontractor_id;
        }

        public void setSubcontractor_id(int subcontractor_id) {
            this.subcontractor_id = subcontractor_id;
        }

        public String getSubcontractor_name() {
            return subcontractor_name;
        }

        public void setSubcontractor_name(String subcontractor_name) {
            this.subcontractor_name = subcontractor_name;
        }

        public String getSubcontractor_short_name() {
            return subcontractor_short_name;
        }

        public void setSubcontractor_short_name(String subcontractor_short_name) {
            this.subcontractor_short_name = subcontractor_short_name;
        }
    }
}
