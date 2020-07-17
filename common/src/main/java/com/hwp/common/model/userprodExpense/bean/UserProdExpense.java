package com.hwp.common.model.userprodExpense.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserProdExpense implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String prodSubId;
    private BigDecimal trsAmount;
    private String prodId;
    private String trsPassword;
    private String trsPasswprdFlag;
    private String acctNo;
    private String transTime;
    private String transDate;
    private BigDecimal totalShare;
    private BigDecimal loadingShare;
    private String channelJnlNo;
    private String jsJnlNo;
    private String appointFlg;
    private String reMsg;
    private String status;
    private String trsType;
    private String dataStatus;
    private String uuid;
    private String oriJnlNo;
    private String createTime;
    private String statusRetuenMsg;
    private String statusReturnCode;
    private String retuenMsg;
    private String returnCode;
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

    public String getProdSubId() {
        return prodSubId;
    }

    public void setProdSubId(String prodSubId) {
        this.prodSubId = prodSubId == null ? null : prodSubId.trim();
    }

    public BigDecimal getTrsAmount() {
        return trsAmount;
    }

    public void setTrsAmount(BigDecimal trsAmount) {
        this.trsAmount = trsAmount;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getTrsPassword() {
        return trsPassword;
    }

    public void setTrsPassword(String trsPassword) {
        this.trsPassword = trsPassword == null ? null : trsPassword.trim();
    }

    public String getTrsPasswprdFlag() {
        return trsPasswprdFlag;
    }

    public void setTrsPasswprdFlag(String trsPasswprdFlag) {
        this.trsPasswprdFlag = trsPasswprdFlag == null ? null : trsPasswprdFlag.trim();
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime == null ? null : transTime.trim();
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    public BigDecimal getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(BigDecimal totalShare) {
        this.totalShare = totalShare;
    }

    public BigDecimal getLoadingShare() {
        return loadingShare;
    }

    public void setLoadingShare(BigDecimal loadingShare) {
        this.loadingShare = loadingShare;
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

    public String getAppointFlg() {
        return appointFlg;
    }

    public void setAppointFlg(String appointFlg) {
        this.appointFlg = appointFlg == null ? null : appointFlg.trim();
    }

    public String getReMsg() {
        return reMsg;
    }

    public void setReMsg(String reMsg) {
        this.reMsg = reMsg == null ? null : reMsg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTrsType() {
        return trsType;
    }

    public void setTrsType(String trsType) {
        this.trsType = trsType == null ? null : trsType.trim();
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

    public String getOriJnlNo() {
        return oriJnlNo;
    }

    public void setOriJnlNo(String oriJnlNo) {
        this.oriJnlNo = oriJnlNo == null ? null : oriJnlNo.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getStatusRetuenMsg() {
        return statusRetuenMsg;
    }

    public void setStatusRetuenMsg(String statusRetuenMsg) {
        this.statusRetuenMsg = statusRetuenMsg == null ? null : statusRetuenMsg.trim();
    }

    public String getStatusReturnCode() {
        return statusReturnCode;
    }

    public void setStatusReturnCode(String statusReturnCode) {
        this.statusReturnCode = statusReturnCode == null ? null : statusReturnCode.trim();
    }

    public String getRetuenMsg() {
        return retuenMsg;
    }

    public void setRetuenMsg(String retuenMsg) {
        this.retuenMsg = retuenMsg == null ? null : retuenMsg.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}