package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/7/7.
 */

public class PhotographPersonnerBean {

    /**
     * code : 0
     * msg : success
     * data : {"facial_photo":""}
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
         * facial_photo :
         */

        private String facial_photo;

        public String getFacial_photo() {
            return facial_photo;
        }

        public void setFacial_photo(String facial_photo) {
            this.facial_photo = facial_photo;
        }
    }
}
