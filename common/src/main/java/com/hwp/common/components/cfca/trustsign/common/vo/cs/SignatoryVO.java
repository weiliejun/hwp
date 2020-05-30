// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignatoryVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class SignatoryVO {

    private String userName;
    private String identTypeCode;
    private String identNo;
    private Integer signmentState;
    private Long signTime;
    private String refuseReason;

    public SignatoryVO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentTypeCode() {
        return identTypeCode;
    }

    public void setIdentTypeCode(String identTypeCode) {
        this.identTypeCode = identTypeCode;
    }

    public String getIdentNo() {
        return identNo;
    }

    public void setIdentNo(String identNo) {
        this.identNo = identNo;
    }

    public Integer getSignmentState() {
        return signmentState;
    }

    public void setSignmentState(Integer signmentState) {
        this.signmentState = signmentState;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String toString() {
        return (new StringBuilder()).append("SignatoryVO [\nuserName=").append(userName).append("\nidentTypeCode=").append(identTypeCode).append("\nidentNo=").append(identNo).append("\nsignmentState=").append(signmentState).append("\nsignTime=").append(signTime).append("\nrefuseReason=").append(refuseReason).append("\n]").toString();
    }
}
