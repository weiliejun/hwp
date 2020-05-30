// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CertApplyVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class CertApplyVO {

    private String userId;
    private String p10;
    private String serialNo;
    private String dn;
    private String startTime;
    private String endTime;
    private String signCert;

    public CertApplyVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getP10() {
        return p10;
    }

    public void setP10(String p10) {
        this.p10 = p10;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSignCert() {
        return signCert;
    }

    public void setSignCert(String signCert) {
        this.signCert = signCert;
    }

    public String toString() {
        return (new StringBuilder()).append("CertApplyVO [\nuserId=").append(userId).append("\np10=").append(p10).append("\nserialNo=").append(serialNo).append("\ndn=").append(dn).append("\nstartTime=").append(startTime).append("\nendTime=").append(endTime).append("\nsignCert=").append(signCert).append("\n]").toString();
    }
}
