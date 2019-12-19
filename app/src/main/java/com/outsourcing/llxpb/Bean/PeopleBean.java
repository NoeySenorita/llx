package com.outsourcing.llxpb.Bean;

/**
 * Created by DELL on 2019/10/9.
 */

public class PeopleBean {

    /**
     * code : 0
     * message : success
     * data : {"in_project_person_count":1,"overage_person_count":1,"local_person_count":1,"unlocal_person_count":1,"year_person_count":1,"accumulative_person_count":1}
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
         * in_project_person_count : 1
         * overage_person_count : 1
         * local_person_count : 1
         * unlocal_person_count : 1
         * year_person_count : 1
         * accumulative_person_count : 1
         */

        private int in_project_person_count;
        private int overage_person_count;
        private int local_person_count;
        private int unlocal_person_count;
        private int year_person_count;
        private int accumulative_person_count;

        public int getIn_project_person_count() {
            return in_project_person_count;
        }

        public void setIn_project_person_count(int in_project_person_count) {
            this.in_project_person_count = in_project_person_count;
        }

        public int getOverage_person_count() {
            return overage_person_count;
        }

        public void setOverage_person_count(int overage_person_count) {
            this.overage_person_count = overage_person_count;
        }

        public int getLocal_person_count() {
            return local_person_count;
        }

        public void setLocal_person_count(int local_person_count) {
            this.local_person_count = local_person_count;
        }

        public int getUnlocal_person_count() {
            return unlocal_person_count;
        }

        public void setUnlocal_person_count(int unlocal_person_count) {
            this.unlocal_person_count = unlocal_person_count;
        }

        public int getYear_person_count() {
            return year_person_count;
        }

        public void setYear_person_count(int year_person_count) {
            this.year_person_count = year_person_count;
        }

        public int getAccumulative_person_count() {
            return accumulative_person_count;
        }

        public void setAccumulative_person_count(int accumulative_person_count) {
            this.accumulative_person_count = accumulative_person_count;
        }
    }
}
