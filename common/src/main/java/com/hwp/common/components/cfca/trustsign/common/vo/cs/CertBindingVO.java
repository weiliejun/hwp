// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CertBindingVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class CertBindingVO {

    private String userId;
    private String signCert;

    public CertBindingVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignCert() {
        return signCert;
    }

    public void setSignCert(String signCert) {
        this.signCert = signCert;
    }

    public String toString() {
        return (new StringBuilder()).append("CertBindingVO [\nuserId=").append(userId).append("\nsignCert=").append(signCert).append("\n]").toString();
    }
}
