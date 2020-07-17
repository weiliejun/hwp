package com.hwp.common.model.userTransaction.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String uuid;
    private String userId;
    private String payNo;
    private String withdradalNo;
    private BigDecimal trsAmount;
    private BigDecimal trsBalance;
    private String tradeStatus;
    private String rtxnTypeCode;
    private String txnTime;
    private String trsEndTime;
    private String rexnTypeName;
    private String flowType;
    private String orderId;
    private String transId;
    private String returnCode;
    private String returnMsg;
    private String dataStatus;
    private String createTime;
    private String remark;
    private String billTrsAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getWithdradalNo() {
        return withdradalNo;
    }

    public void setWithdradalNo(String withdradalNo) {
        this.withdradalNo = withdradalNo == null ? null : withdradalNo.trim();
    }

    public BigDecimal getTrsAmount() {
        return trsAmount;
    }

    public void setTrsAmount(BigDecimal trsAmount) {
        this.trsAmount = trsAmount;
    }

    public BigDecimal getTrsBalance() {
        return trsBalance;
    }

    public void setTrsBalance(BigDecimal trsBalance) {
        this.trsBalance = trsBalance;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getRtxnTypeCode() {
        return rtxnTypeCode;
    }

    public void setRtxnTypeCode(String rtxnTypeCode) {
        this.rtxnTypeCode = rtxnTypeCode == null ? null : rtxnTypeCode.trim();
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime == null ? null : txnTime.trim();
    }

    public String getTrsEndTime() {
        return trsEndTime;
    }

    public void setTrsEndTime(String trsEndTime) {
        this.trsEndTime = trsEndTime == null ? null : trsEndTime.trim();
    }

    public String getRexnTypeName() {
        return rexnTypeName;
    }

    public void setRexnTypeName(String rexnTypeName) {
        this.rexnTypeName = rexnTypeName == null ? null : rexnTypeName.trim();
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType == null ? null : flowType.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId == null ? null : transId.trim();
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

    public String getBillTrsAmount() {
        return billTrsAmount;
    }

    public void setBillTrsAmount(String billTrsAmount) {
        this.billTrsAmount = billTrsAmount;
    }
}