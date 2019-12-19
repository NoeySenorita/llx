package com.outsourcing.llxpb.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2019/7/4.
 */

public class PersonnelListBean implements Serializable{


    /**
     * code : 0
     * msg : success
     * data : {"total":100,"page":1,"page_size":999,"migrant_worker_list":[{"id":1,"name":"","sex":1,"age":1,"native_place":"","work_type_id":1,"work_type_name":"","info_completed":1,"project_status":1}],"page_total":1,"in_project_total":1}
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
         * total : 100
         * page : 1
         * page_size : 999
         * migrant_worker_list : [{"id":1,"name":"","sex":1,"age":1,"native_place":"","work_type_id":1,"work_type_name":"","info_completed":1,"project_status":1}]
         * page_total : 1
         * in_project_total : 1
         */

        private int total;
        private int page;
        private int page_size;
        private int page_total;
        private int in_project_total;
        private List<MigrantWorkerListBean> migrant_worker_list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
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

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public int getIn_project_total() {
            return in_project_total;
        }

        public void setIn_project_total(int in_project_total) {
            this.in_project_total = in_project_total;
        }

        public List<MigrantWorkerListBean> getMigrant_worker_list() {
            return migrant_worker_list;
        }

        public void setMigrant_worker_list(List<MigrantWorkerListBean> migrant_worker_list) {
            this.migrant_worker_list = migrant_worker_list;
        }

        public static class MigrantWorkerListBean implements Serializable {
            /**
             * id : 1
             * name :
             * sex : 1
             * age : 1
             * native_place :
             * work_type_id : 1
             * work_type_name :
             * info_completed : 1
             * project_status : 1
             */

            private int id;
            private String name;
            private int sex;
            private int age;
            private String native_place;
            private int work_type_id;
            private String work_type_name;
            private int info_completed;
            private int project_status;

            public boolean isStateSelect() {
                return stateSelect;
            }

            public void setStateSelect(boolean stateSelect) {
                this.stateSelect = stateSelect;
            }

            private boolean stateSelect;
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getNative_place() {
                return native_place;
            }

            public void setNative_place(String native_place) {
                this.native_place = native_place;
            }

            public int getWork_type_id() {
                return work_type_id;
            }

            public void setWork_type_id(int work_type_id) {
                this.work_type_id = work_type_id;
            }

            public String getWork_type_name() {
                return work_type_name;
            }

            public void setWork_type_name(String work_type_name) {
                this.work_type_name = work_type_name;
            }

            public int getInfo_completed() {
                return info_completed;
            }

            public void setInfo_completed(int info_completed) {
                this.info_completed = info_completed;
            }

            public int getProject_status() {
                return project_status;
            }

            public void setProject_status(int project_status) {
                this.project_status = project_status;
            }
        }
    }
}
