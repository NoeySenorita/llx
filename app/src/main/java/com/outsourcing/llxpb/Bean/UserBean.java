package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/7/2.
 */

public class UserBean {


    /**
     * code : 0
     * message : success
     * data : {"uuid":"71af0e25016794dfdba5743beaec6b6f","token":"eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MTE0OTUwMzM3MTA3NTA2NzkwNSwicm9sZVR5cGUiOjIwLCJleHAiOjE1NjI5MjI0NDEsImlhdCI6MTU2MjkxODg0MSwicm9sZXMiOm51bGx9.UontZVx7lpA7NLPrNTDniJ4pEyoG0g7zZS1gazDGsKiu-NyIMYeYr0DPu-026yWOqNQpOeEj2GXkizp-qGHd1Q","role_type":20,"project_count":0,"default_project_id":0,"default_project_role_id":0,"default_project_company_id":0,"default_project_organization_id":0,"refresh_token":""}
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
         * uuid : 71af0e25016794dfdba5743beaec6b6f
         * token : eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MTE0OTUwMzM3MTA3NTA2NzkwNSwicm9sZVR5cGUiOjIwLCJleHAiOjE1NjI5MjI0NDEsImlhdCI6MTU2MjkxODg0MSwicm9sZXMiOm51bGx9.UontZVx7lpA7NLPrNTDniJ4pEyoG0g7zZS1gazDGsKiu-NyIMYeYr0DPu-026yWOqNQpOeEj2GXkizp-qGHd1Q
         * role_type : 20
         * project_count : 0
         * default_project_id : 0
         * default_project_role_id : 0
         * default_project_company_id : 0
         * default_project_organization_id : 0
         * refresh_token :
         */

        private String uuid;
        private String token;
        private int role_type;
        private int project_count;
        private int default_project_id;
        private int default_project_role_id;
        private int default_project_company_id;
        private int default_project_organization_id;
        private String refresh_token;

        public String getDefault_project_short_name() {
            return default_project_short_name;
        }

        public void setDefault_project_short_name(String default_project_short_name) {
            this.default_project_short_name = default_project_short_name;
        }

        private String default_project_short_name;

        public String getDefault_project_name() {
            return default_project_name;
        }

        public void setDefault_project_name(String default_project_name) {
            this.default_project_name = default_project_name;
        }

        private String default_project_name;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getRole_type() {
            return role_type;
        }

        public void setRole_type(int role_type) {
            this.role_type = role_type;
        }

        public int getProject_count() {
            return project_count;
        }

        public void setProject_count(int project_count) {
            this.project_count = project_count;
        }

        public int getDefault_project_id() {
            return default_project_id;
        }

        public void setDefault_project_id(int default_project_id) {
            this.default_project_id = default_project_id;
        }

        public int getDefault_project_role_id() {
            return default_project_role_id;
        }

        public void setDefault_project_role_id(int default_project_role_id) {
            this.default_project_role_id = default_project_role_id;
        }

        public int getDefault_project_company_id() {
            return default_project_company_id;
        }

        public void setDefault_project_company_id(int default_project_company_id) {
            this.default_project_company_id = default_project_company_id;
        }

        public int getDefault_project_organization_id() {
            return default_project_organization_id;
        }

        public void setDefault_project_organization_id(int default_project_organization_id) {
            this.default_project_organization_id = default_project_organization_id;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }
}
