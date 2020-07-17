package com.hwp.common.model.tenderRepayment.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TenderRepayment implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String tenderId;
    private String userInfoId;
    private String uuid;
    private String productId;
    private String repayType;
    private String proNum;
    private String channelJnlNo;
    private String jsJnlNo;
    private String batchCode;
    private String hxAcctNo;
    private String repayPlanDate;
    private BigDecimal totalAmount;
    private BigDecimal totalCount;
    private String payList;
    private String requestContent;
    private String respContent;
    private String returnCode;
    private String returnMsg;
    private String status;
    private String remark;
    private String createTime;
    private String dataStatus;
    private String repayTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId == null ? null : tenderId.trim();
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType == null ? null : repayType.trim();
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum == null ? null : proNum.trim();
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

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    public String getHxAcctNo() {
        return hxAcctNo;
    }

    public void setHxAcctNo(String hxAcctNo) {
        this.hxAcctNo = hxAcctNo == null ? null : hxAcctNo.trim();
    }

    public String getRepayPlanDate() {
        return repayPlanDate;
    }

    public void setRepayPlanDate(String repayPlanDate) {
        this.repayPlanDate = repayPlanDate == null ? null : repayPlanDate.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public String getPayList() {
        return payList;
    }

    public void setPayList(String payList) {
        this.payList = payList == null ? null : payList.trim();
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent == null ? null : requestContent.trim();
    }

    public String getRespContent() {
        return respContent;
    }

    public void setRespContent(String respContent) {
        this.respContent = respContent == null ? null : respContent.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(String repayTime) {
        this.repayTime = repayTime == null ? null : repayTime.trim();
    }
}