package com.hwp.common.model.prodInfo.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String prodName;
    private String prodId;
    private BigDecimal profitPerAcc;
    private String navDate;
    private String merchantNo;
    private BigDecimal annualReturnBys;
    private String prodSubId;
    private String taCode;
    private String prodType;
    private String prodSubName;
    private String dataStatus;
    private String createTime;
    private String updateTime;
    private String returnCode;
    private String returnMsg;
    private String prodSubDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public BigDecimal getProfitPerAcc() {
        return profitPerAcc;
    }

    public void setProfitPerAcc(BigDecimal profitPerAcc) {
        this.profitPerAcc = profitPerAcc;
    }

    public String getNavDate() {
        return navDate;
    }

    public void setNavDate(String navDate) {
        this.navDate = navDate == null ? null : navDate.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public BigDecimal getAnnualReturnBys() {
        return annualReturnBys;
    }

    public void setAnnualReturnBys(BigDecimal annualReturnBys) {
        this.annualReturnBys = annualReturnBys;
    }

    public String getProdSubId() {
        return prodSubId;
    }

    public void setProdSubId(String prodSubId) {
        this.prodSubId = prodSubId == null ? null : prodSubId.trim();
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode == null ? null : taCode.trim();
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    public String getProdSubName() {
        return prodSubName;
    }

    public void setProdSubName(String prodSubName) {
        this.prodSubName = prodSubName == null ? null : prodSubName.trim();
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
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

    public String getProdSubDesc() {
        return prodSubDesc;
    }

    public void setProdSubDesc(String prodSubDesc) {
        this.prodSubDesc = prodSubDesc == null ? null : prodSubDesc.trim();
    }
}