package com.outsourcing.llxpb.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2019/10/9.
 */

public class MessageBean implements Serializable {


    /**
     * code : 0
     * message : success
     * data : {"unread_num":3,"notice_list":[{"id":3,"content":"祖国70年大庆，项目组特地安排了一场千人同庆的盛宴，希望大家踊跃参加。","project_id":2,"notice_type":200,"is_top":1,"release_time":1569823200000,"read_time":0},{"id":4,"content":"关于国庆节后项目重要安排：经项目组商定，原定于2020.01.01完工的03路段，需要提前到2019.12.10号完成。","project_id":2,"notice_type":200,"is_top":0,"release_time":1570521751325,"read_time":0},{"id":2,"content":"喜迎中秋，共庆团圆。项目组全体休假一天。","project_id":2,"notice_type":200,"is_top":0,"release_time":1568260800000,"read_time":0},{"id":1,"content":"为了积极响应国家政策号召，从即日起，所有农民工必须实名登记。","project_id":2,"notice_type":200,"is_top":0,"release_time":1559444400000,"read_time":1570530158601}]}
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
         * unread_num : 3
         * notice_list : [{"id":3,"content":"祖国70年大庆，项目组特地安排了一场千人同庆的盛宴，希望大家踊跃参加。","project_id":2,"notice_type":200,"is_top":1,"release_time":1569823200000,"read_time":0},{"id":4,"content":"关于国庆节后项目重要安排：经项目组商定，原定于2020.01.01完工的03路段，需要提前到2019.12.10号完成。","project_id":2,"notice_type":200,"is_top":0,"release_time":1570521751325,"read_time":0},{"id":2,"content":"喜迎中秋，共庆团圆。项目组全体休假一天。","project_id":2,"notice_type":200,"is_top":0,"release_time":1568260800000,"read_time":0},{"id":1,"content":"为了积极响应国家政策号召，从即日起，所有农民工必须实名登记。","project_id":2,"notice_type":200,"is_top":0,"release_time":1559444400000,"read_time":1570530158601}]
         */

        private int unread_num;
        private List<NoticeListBean> notice_list;

        public int getUnread_num() {
            return unread_num;
        }

        public void setUnread_num(int unread_num) {
            this.unread_num = unread_num;
        }

        public List<NoticeListBean> getNotice_list() {
            return notice_list;
        }

        public void setNotice_list(List<NoticeListBean> notice_list) {
            this.notice_list = notice_list;
        }

        public static class NoticeListBean {
            /**
             * id : 3
             * content : 祖国70年大庆，项目组特地安排了一场千人同庆的盛宴，希望大家踊跃参加。
             * project_id : 2
             * notice_type : 200
             * is_top : 1
             * release_time : 1569823200000
             * read_time : 0
             */

            private int id;
            private String content;
            private int project_id;
            private int notice_type;
            private int is_top;
            private long release_time;
            private long read_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getProject_id() {
                return project_id;
            }

            public void setProject_id(int project_id) {
                this.project_id = project_id;
            }

            public int getNotice_type() {
                return notice_type;
            }

            public void setNotice_type(int notice_type) {
                this.notice_type = notice_type;
            }

            public int getIs_top() {
                return is_top;
            }

            public void setIs_top(int is_top) {
                this.is_top = is_top;
            }

            public long getRelease_time() {
                return release_time;
            }

            public void setRelease_time(long release_time) {
                this.release_time = release_time;
            }

            public long getRead_time() {
                return read_time;
            }

            public void setRead_time(long read_time) {
                this.read_time = read_time;
            }
        }
    }
}
