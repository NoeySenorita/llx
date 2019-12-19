package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/7/18.
 */

public class SelectTypeWorkBean {
    public List<TypeWork> getTypeWorkList() {
        return mTypeWorkList;
    }

    public void setTypeWorkList(List<TypeWork> typeWorkList) {
        mTypeWorkList = typeWorkList;
    }

    private List<TypeWork> mTypeWorkList;

    class TypeWork{
        public String getTypeWorkName() {
            return TypeWorkName;
        }

        public void setTypeWorkName(String typeWorkName) {
            TypeWorkName = typeWorkName;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public String getTimeStop() {
            return timeStop;
        }

        public void setTimeStop(String timeStop) {
            this.timeStop = timeStop;
        }

        String TypeWorkName;
        boolean select;
        String  number;
        String timeStart;
        String timeStop;
    }
}
