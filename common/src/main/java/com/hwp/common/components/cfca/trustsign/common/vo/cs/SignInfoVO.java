// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignInfoVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class SignInfoVO {

    private String userId;
    private String authorizationTime;
    private String location;
    private String signLocation;
    private String projectCode;
    private Integer isProxySign;
    private Integer isCopy;
    private String signCert;
    private String imageData;

    public SignInfoVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorizationTime() {
        return authorizationTime;
    }

    public void setAuthorizationTime(String authorizationTime) {
        this.authorizationTime = authorizationTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSignLocation() {
        return signLocation;
    }

    public void setSignLocation(String signLocation) {
        this.signLocation = signLocation;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getIsProxySign() {
        return isProxySign;
    }

    public void setIsProxySign(Integer isProxySign) {
        this.isProxySign = isProxySign;
    }

    public Integer getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(Integer isCopy) {
        this.isCopy = isCopy;
    }

    public String getSignCert() {
        return signCert;
    }

    public void setSignCert(String signCert) {
        this.signCert = signCert;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String toString() {
        return (new StringBuilder()).append("SignInfoVO [\nuserId=").append(userId).append("\nauthorizationTime=").append(authorizationTime).append("\nlocation=").append(location).append("\nsignLocation=").append(signLocation).append("\nprojectCode=").append(projectCode).append("\nisProxySign=").append(isProxySign).append("\nisCopy=").append(isCopy).append("\nsignCert=").append(signCert).append("\nimageData=").append(imageData).append("\n]").toString();
    }
}
