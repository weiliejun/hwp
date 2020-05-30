// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HeadVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class HeadVO {

    public static final String CODE_SUCCESS = "60000000";
    public static final String MESSAGE_SUCCESS = "OK";
    private String txTime;
    private String locale;
    private String remark;
    private String retCode;
    private String retMessage;

    public HeadVO() {
    }

    public String getTxTime() {
        return txTime;
    }

    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public String toString() {
        return (new StringBuilder()).append("HeadVO [\ntxTime=").append(txTime).append("\nlocale=").append(locale).append("\nremark=").append(remark).append("\nretCode=").append(retCode).append("\nretMessage=").append(retMessage).append("\n]").toString();
    }
}
