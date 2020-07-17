package com.hwp.common.model.sysMessage.bean;

import java.io.Serializable;

public class UserMessageSet implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9206256009389651477L;

    private String id;

    private String userInfoId;

    private String busiType;

    private String smsStatus;

    private String emailStatus;

    private String websiteStatus;

    private String createTime;

    private String dataStatus;

    private String description;

    private String remark;

    private String wechatStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getWebsiteStatus() {
        return websiteStatus;
    }

    public void setWebsiteStatus(String websiteStatus) {
        this.websiteStatus = websiteStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWechatStatus() {
        return wechatStatus;
    }

    public void setWechatStatus(String wechatStatus) {
        this.wechatStatus = wechatStatus;
    }
}