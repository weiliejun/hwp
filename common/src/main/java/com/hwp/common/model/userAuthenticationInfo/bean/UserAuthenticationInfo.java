package com.hwp.common.model.userAuthenticationInfo.bean;

import java.io.Serializable;

/**
 * 用户认证
 */
public class UserAuthenticationInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String uuid;
    private String userName;
    private String idNo;
    private String uploadIdentityStatus;
    private String returnCode;
    private String returnMsg;
    private String createTime;
    private String dataStatus;
    private String identityFront;
    private String inentityBack;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getUploadIdentityStatus() {
        return uploadIdentityStatus;
    }

    public void setUploadIdentityStatus(String uploadIdentityStatus) {
        this.uploadIdentityStatus = uploadIdentityStatus == null ? null : uploadIdentityStatus.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public String getIdentityFront() {
        return identityFront;
    }

    public void setIdentityFront(String identityFront) {
        this.identityFront = identityFront == null ? null : identityFront.trim();
    }

    public String getInentityBack() {
        return inentityBack;
    }

    public void setInentityBack(String inentityBack) {
        this.inentityBack = inentityBack == null ? null : inentityBack.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}