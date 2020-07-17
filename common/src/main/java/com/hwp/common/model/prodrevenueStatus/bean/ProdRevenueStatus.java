package com.hwp.common.model.prodrevenueStatus.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdRevenueStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String prodId;
    private String prodSubId;
    private String profitLastDate;
    private BigDecimal lastProfit;
    private BigDecimal totalShare;
    private BigDecimal dyAmount;
    private String accountNo;
    private BigDecimal loadingShare;
    private BigDecimal totalAllProfits;
    private BigDecimal profitPerAcct;
    private String signResult;
    private String dataStatus;
    private String createTime;
    private String uuid;
    private String returnCode;
    private String retuenMsg;
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

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdSubId() {
        return prodSubId;
    }

    public void setProdSubId(String prodSubId) {
        this.prodSubId = prodSubId == null ? null : prodSubId.trim();
    }

    public String getProfitLastDate() {
        return profitLastDate;
    }

    public void setProfitLastDate(String profitLastDate) {
        this.profitLastDate = profitLastDate == null ? null : profitLastDate.trim();
    }

    public BigDecimal getLastProfit() {
        return lastProfit;
    }

    public void setLastProfit(BigDecimal lastProfit) {
        this.lastProfit = lastProfit;
    }

    public BigDecimal getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(BigDecimal totalShare) {
        this.totalShare = totalShare;
    }

    public BigDecimal getDyAmount() {
        return dyAmount;
    }

    public void setDyAmount(BigDecimal dyAmount) {
        this.dyAmount = dyAmount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public BigDecimal getLoadingShare() {
        return loadingShare;
    }

    public void setLoadingShare(BigDecimal loadingShare) {
        this.loadingShare = loadingShare;
    }

    public BigDecimal getTotalAllProfits() {
        return totalAllProfits;
    }

    public void setTotalAllProfits(BigDecimal totalAllProfits) {
        this.totalAllProfits = totalAllProfits;
    }

    public BigDecimal getProfitPerAcct() {
        return profitPerAcct;
    }

    public void setProfitPerAcct(BigDecimal profitPerAcct) {
        this.profitPerAcct = profitPerAcct;
    }

    public String getSignResult() {
        return signResult;
    }

    public void setSignResult(String signResult) {
        this.signResult = signResult == null ? null : signResult.trim();
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getRetuenMsg() {
        return retuenMsg;
    }

    public void setRetuenMsg(String retuenMsg) {
        this.retuenMsg = retuenMsg == null ? null : retuenMsg.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}