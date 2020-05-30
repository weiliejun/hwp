// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   EnterpriseTransactorVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class EnterpriseTransactorVO {

    private String transactorName;
    private String identTypeCode;
    private String identNo;
    private String address;

    public EnterpriseTransactorVO() {
    }

    public String getTransactorName() {
        return transactorName;
    }

    public void setTransactorName(String transactorName) {
        this.transactorName = transactorName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return (new StringBuilder()).append("EnterpriseTransactorVO [\ntransactorName=").append(transactorName).append("\nidentTypeCode=").append(identTypeCode).append("\nidentNo=").append(identNo).append("\naddress=").append(address).append("\n]").toString();
    }
}
