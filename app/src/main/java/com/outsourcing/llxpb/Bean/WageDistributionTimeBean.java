package com.outsourcing.llxpb.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2019/8/14.
 */

public class WageDistributionTimeBean implements Serializable {

    /**
     * code : 0
     * message : success
     * data : [201908,201907,201902,201811]
     */

    private int code;
    private String message;
    private List<Integer> data;

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

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
