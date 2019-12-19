package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/10/9.
 */

public class MessageListBean {

    /**
     * code : 0
     * message : success
     * data : {"total":1,"page_total":1,"page":1,"page_size":1,"notice_list":[{"id":1,"notice_type":1,"content":"","release_time":1}]}
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
         * total : 1
         * page_total : 1
         * page : 1
         * page_size : 1
         * notice_list : [{"id":1,"notice_type":1,"content":"","release_time":1}]
         */

        private int total;
        private int page_total;
        private int page;
        private int page_size;
        private List<NoticeListBean> notice_list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public List<NoticeListBean> getNotice_list() {
            return notice_list;
        }

        public void setNotice_list(List<NoticeListBean> notice_list) {
            this.notice_list = notice_list;
        }

        public static class NoticeListBean {
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

            public long getRead_time() {
                return read_time;
            }

            public void setRead_time(long read_time) {
                this.read_time = read_time;
            }

            private long read_time;

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
}
