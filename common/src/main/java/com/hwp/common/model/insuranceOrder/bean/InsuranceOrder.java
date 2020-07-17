package com.hwp.common.model.insuranceOrder.bean;

import java.io.Serializable;

public class InsuranceOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String status;
    private String name;
    private String mobile;
    private String carNumber;
    private String engineNumber;
    private String frameNumber;
    private String remark;
    private String dataStatus;
    private String createTime;
    private String carInsuranceType;
    private String orderInsuranceType;
    private String auditTime;
    private String auditorId;
    private String auditorName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

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
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber == null ? null : engineNumber.trim();
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber == null ? null : frameNumber.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCarInsuranceType() {
        return carInsuranceType;
    }

    public void setCarInsuranceType(String carInsuranceType) {
        this.carInsuranceType = carInsuranceType == null ? null : carInsuranceType.trim();
    }

    public String getOrderInsuranceType() {
        return orderInsuranceType;
    }

    public void setOrderInsuranceType(String orderInsuranceType) {
        this.orderInsuranceType = orderInsuranceType == null ? null : orderInsuranceType.trim();
    }
}