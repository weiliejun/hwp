package com.hwp.common.model.userBalance.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserBalance implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String accountNo;
    private BigDecimal availBalal;
    private BigDecimal depositAvailbal;
    private BigDecimal depositBal;
    private BigDecimal fundBalances;
    private BigDecimal finaBalances;
    private String createTime;
    private String updateTime;
    private String dataStatus;
    private String uuid;
    private BigDecimal localAvailBalall;
    private BigDecimal localDepositAvailbal;
    private BigDecimal localDepositBal;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public BigDecimal getAvailBalal() {
        return availBalal;
    }

    public void setAvailBalal(BigDecimal availBalal) {
        this.availBalal = availBalal;
    }

    public BigDecimal getDepositAvailbal() {
        return depositAvailbal;
    }

    public void setDepositAvailbal(BigDecimal depositAvailbal) {
        this.depositAvailbal = depositAvailbal;
    }

    public BigDecimal getDepositBal() {
        return depositBal;
    }

    public void setDepositBal(BigDecimal depositBal) {
        this.depositBal = depositBal;
    }

    public BigDecimal getFundBalances() {
        return fundBalances;
    }

    public void setFundBalances(BigDecimal fundBalances) {
        this.fundBalances = fundBalances;
    }

    public BigDecimal getFinaBalances() {
        return finaBalances;
    }

    public void setFinaBalances(BigDecimal finaBalances) {
        this.finaBalances = finaBalances;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public BigDecimal getLocalAvailBalall() {
        return localAvailBalall;
    }

    public void setLocalAvailBalall(BigDecimal localAvailBalall) {
        this.localAvailBalall = localAvailBalall;
    }

    public BigDecimal getLocalDepositAvailbal() {
        return localDepositAvailbal;
    }

    public void setLocalDepositAvailbal(BigDecimal localDepositAvailbal) {
        this.localDepositAvailbal = localDepositAvailbal;
    }

    public BigDecimal getLocalDepositBal() {
        return localDepositBal;
    }

    public void setLocalDepositBal(BigDecimal localDepositBal) {
        this.localDepositBal = localDepositBal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}