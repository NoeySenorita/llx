package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/7/27.
 */

public class PhotographPersonnerUpBean {

    /**
     * code : 0
     * message : success
     * data : {"file_name":"8DjZ4QKAtAK4AAAAAASUVORK5CYII=.jpg","web_url":"https://laolixin-b1.oss-cn-hangzhou.aliyuncs.com/data/upload/roster/3/e4abdf9a-2c18-4c6b-895d-3772cee8cf35.jpg"}
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
         * file_name : 8DjZ4QKAtAK4AAAAAASUVORK5CYII=.jpg
         * web_url : https://laolixin-b1.oss-cn-hangzhou.aliyuncs.com/data/upload/roster/3/e4abdf9a-2c18-4c6b-895d-3772cee8cf35.jpg
         */

        private String file_name;
        private String web_url;

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
