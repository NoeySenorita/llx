package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/10/9.
 */

public class MessageInfoBean {

    /**
     * code : 0
     * message : success
     * data : {"id":1,"notice_type":1,"content":"","release_time":1}
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
         * id : 1
         * notice_type : 1
         * content :
         * release_time : 1
         */

        private int id;
        private int notice_type;
        private String content;
        private long release_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNotice_type() {
            return notice_type;
        }

        public void setNotice_type(int notice_type) {
            this.notice_type = notice_type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getRelease_time() {
            return release_time;
        }

        public void setRelease_time(long release_time) {
            this.release_time = release_time;
        }
    }
}
