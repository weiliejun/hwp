// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ProxySignVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class ProxySignVO {

    private String userId;
    private String projectCode;
    private String checkCode;
    private Integer isSendVoice;

    public ProxySignVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Integer getIsSendVoice() {
        return isSendVoice;
    }

    public void setIsSendVoice(Integer isSendVoice) {
        this.isSendVoice = isSendVoice;
    }

    public String toString() {
        return (new StringBuilder()).append("ProxySignVO [\nuserId=").append(userId).append("\nprojectCode=").append(projectCode).append("\ncheckCode=").append(checkCode).append("\nisSendVoice=").append(isSendVoice).append("\n]").toString();
    }
}
