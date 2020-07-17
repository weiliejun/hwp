package com.hwp.common.model.userRecharge.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户充值交易记录
 */
public class UserRecharge implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String uuid;
    private String eaccountNo;
    private String acNo;
    private String trsPassword;
    private BigDecimal trsAmount;
    private String verifyTrsPasswordFlag;
    private BigDecimal trsBalance;
    private String channelJnlNo;
    private String jsJnlNo;
    private String transResult;
    private String returnCode;
    private String returnMsg;
    private String createTime;
    private String dateStatus;
    private String remark;
    private String tradeStatus;
    private String txnTime;
    private String trsEndTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getEaccountNo() {
        return eaccountNo;
    }

    public void setEaccountNo(String eaccountNo) {
        this.eaccountNo = eaccountNo == null ? null : eaccountNo.trim();
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo == null ? null : acNo.trim();
    }

    public String getTrsPassword() {
        return trsPassword;
    }

    public void setTrsPassword(String trsPassword) {
        this.trsPassword = trsPassword == null ? null : trsPassword.trim();
    }

    public BigDecimal getTrsAmount() {
        return trsAmount;
    }

    public void setTrsAmount(BigDecimal trsAmount) {
        this.trsAmount = trsAmount;
    }

    public String getVerifyTrsPasswordFlag() {
        return verifyTrsPasswordFlag;
    }

    public void setVerifyTrsPasswordFlag(String verifyTrsPasswordFlag) {
        this.verifyTrsPasswordFlag = verifyTrsPasswordFlag == null ? null : verifyTrsPasswordFlag.trim();
    }

    public BigDecimal getTrsBalance() {
        return trsBalance;
    }

    public void setTrsBalance(BigDecimal trsBalance) {
        this.trsBalance = trsBalance;
    }

    public String getChannelJnlNo() {
        return channelJnlNo;
    }

    public void setChannelJnlNo(String channelJnlNo) {
        this.channelJnlNo = channelJnlNo == null ? null : channelJnlNo.trim();
    }

    public String getJsJnlNo() {
        return jsJnlNo;
    }

    public void setJsJnlNo(String jsJnlNo) {
        this.jsJnlNo = jsJnlNo == null ? null : jsJnlNo.trim();
    }

    public String getTransResult() {
        return transResult;
    }

    public void setTransResult(String transResult) {
        this.transResult = transResult == null ? null : transResult.trim();
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

    public String getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(String dateStatus) {
        this.dateStatus = dateStatus == null ? null : dateStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getTrsEndTime() {
        return trsEndTime;
    }

    public void setTrsEndTime(String trsEndTime) {
        this.trsEndTime = trsEndTime;
    }
}