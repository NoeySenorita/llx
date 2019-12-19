package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/10/14.
 */

public class UserInfoMessageBean {

    /**
     * code : 0
     * message : success
     * data : {"name":"吴海洋","sex":1,"profile_picture":"","role_name":"分包负责人","subcontractor_short_name":"武汉康华","subcontractor_name":"武汉康华劳务分包有限公司"}
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
         * name : 吴海洋
         * sex : 1
         * profile_picture :
         * role_name : 分包负责人
         * subcontractor_short_name : 武汉康华
         * subcontractor_name : 武汉康华劳务分包有限公司
         */

        private String name;
        private int sex;
        private String profile_picture;
        private String role_name;
        private String subcontractor_short_name;
        private String subcontractor_name;

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

        public String getProfile_picture() {
            return profile_picture;
        }

        public void setProfile_picture(String profile_picture) {
            this.profile_picture = profile_picture;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public String getSubcontractor_short_name() {
            return subcontractor_short_name;
        }

        public void setSubcontractor_short_name(String subcontractor_short_name) {
            this.subcontractor_short_name = subcontractor_short_name;
        }

        public String getSubcontractor_name() {
            return subcontractor_name;
        }

        public void setSubcontractor_name(String subcontractor_name) {
            this.subcontractor_name = subcontractor_name;
        }
    }
}
