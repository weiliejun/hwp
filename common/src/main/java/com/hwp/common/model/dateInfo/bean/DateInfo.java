package com.hwp.common.model.dateInfo.bean;

import java.io.Serializable;

public class DateInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String time;
    private String dateType;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType == null ? null : dateType.trim();
    }
}