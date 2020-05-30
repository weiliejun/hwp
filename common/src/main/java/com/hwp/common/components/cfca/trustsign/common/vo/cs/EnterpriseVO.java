// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   EnterpriseVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class EnterpriseVO {

    private String userId;
    private String enterpriseName;
    private String identTypeCode;
    private String identNo;
    private String email;
    private String mobilePhone;
    private String landlinePhone;
    private String authenticationMode;
    private String anXinSignEmail;
    private String anXinSignMobilePhone;

    public EnterpriseVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getLandlinePhone() {
        return landlinePhone;
    }

    public void setLandlinePhone(String landlinePhone) {
        this.landlinePhone = landlinePhone;
    }

    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    public String getAnXinSignEmail() {
        return anXinSignEmail;
    }

    public void setAnXinSignEmail(String anXinSignEmail) {
        this.anXinSignEmail = anXinSignEmail;
    }

    public String getAnXinSignMobilePhone() {
        return anXinSignMobilePhone;
    }

    public void setAnXinSignMobilePhone(String anXinSignMobilePhone) {
        this.anXinSignMobilePhone = anXinSignMobilePhone;
    }

    public String toString() {
        return (new StringBuilder()).append("EnterpriseVO [\nuserId=").append(userId).append("\nenterpriseName=").append(enterpriseName).append("\nidentTypeCode=").append(identTypeCode).append("\nidentNo=").append(identNo).append("\nemail=").append(email).append("\nmobilePhone=").append(mobilePhone).append("\nlandlinePhone=").append(landlinePhone).append("\nauthenticationMode=").append(authenticationMode).append("\nanXinSignEmail=").append(anXinSignEmail).append("\nanXinSignMobilePhone=").append(anXinSignMobilePhone).append("\n]").toString();
    }
}
