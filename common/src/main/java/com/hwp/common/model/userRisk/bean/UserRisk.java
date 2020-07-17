package com.hwp.common.model.userRisk.bean;

import java.io.Serializable;

/**
 * 用户风险
 */
public class UserRisk implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String uuid;
    private String riskFlag;
    private String score;
    private String riskLev;
    private String effectiveDate;
    private String detail;
    private String riskLevExpla;
    private String returnCode;
    private String returnMsg;
    private String dataStatus;
    private String createTime;
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

    public String getRiskFlag() {
        return riskFlag;
    }

    public void setRiskFlag(String riskFlag) {
        this.riskFlag = riskFlag == null ? null : riskFlag.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getRiskLev() {
        return riskLev;
    }

    public void setRiskLev(String riskLev) {
        this.riskLev = riskLev == null ? null : riskLev.trim();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getRiskLevExpla() {
        return riskLevExpla;
    }

    public void setRiskLevExpla(String riskLevExpla) {
        this.riskLevExpla = riskLevExpla == null ? null : riskLevExpla.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}