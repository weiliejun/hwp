// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CertUnbindingVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class CertUnbindingVO {

    private String userId;
    private String serialNo;

    public CertUnbindingVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String toString() {
        return (new StringBuilder()).append("CertUnbindingVO [\nuserId=").append(userId).append("\nserialNo=").append(serialNo).append("\n]").toString();
    }
}
