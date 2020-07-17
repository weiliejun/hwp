package com.hwp.common.model.product.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String code;
    private String geName;
    private String geCode;
    private BigDecimal amount;
    private BigDecimal timeLimit;
    private String timeLimitUnit;
    private BigDecimal annualRate;
    private String repayType;
    private String tenderStartTime;
    private String tenderEndTime;
    private BigDecimal tenderInitAmount;
    private BigDecimal tenderIncreaseAmount;
    private String hxName;
    private String hxAcctNo;
    private String auditStatus;
    private String auditDesc;
    private String auditTime;
    private String auditorId;
    private String auditorName;
    private String remark;
    private String dataStatus;
    private String createTime;
    private String creatorId;
    private String creatorName;
    private String editTime;
    private String editorId;
    private String editorName;
    private BigDecimal tenderAmount;
    private BigDecimal tenderUsers;
    private String tenderAuditorId;
    private String tenderAuditorName;
    private String tenderAuditTime;
    private String repayStartDate;
    private String repayEndDate;
    private String publishStatus;
    private String publishTime;
    private String publisherId;
    private String publisherName;
    private String status;
    private String tenderAuditStatus;
    private String productAgreementType;
    private BigDecimal highSingleInvest;
    private String contractNo;
    private String trustContractNo;
    private String proId;
    private String ifPrepay;
    private String purchaseDesc;
    private String productAdvantage;
    private String reasonsPrepay;
    private String projectSummary;
    private String syAmount;
    private String bfbAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getGeName() {
        return geName;
    }

    public void setGeName(String geName) {
        this.geName = geName == null ? null : geName.trim();
    }

    public String getGeCode() {
        return geCode;
    }

    public void setGeCode(String geCode) {
        this.geCode = geCode == null ? null : geCode.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(BigDecimal timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTimeLimitUnit() {
        return timeLimitUnit;
    }

    public void setTimeLimitUnit(String timeLimitUnit) {
        this.timeLimitUnit = timeLimitUnit == null ? null : timeLimitUnit.trim();
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType == null ? null : repayType.trim();
    }

    public String getTenderStartTime() {
        return tenderStartTime;
    }

    public void setTenderStartTime(String tenderStartTime) {
        this.tenderStartTime = tenderStartTime == null ? null : tenderStartTime.trim();
    }

    public String getTenderEndTime() {
        return tenderEndTime;
    }

    public void setTenderEndTime(String tenderEndTime) {
        this.tenderEndTime = tenderEndTime == null ? null : tenderEndTime.trim();
    }

    public BigDecimal getTenderInitAmount() {
        return tenderInitAmount;
    }

    public void setTenderInitAmount(BigDecimal tenderInitAmount) {
        this.tenderInitAmount = tenderInitAmount;
    }

    public BigDecimal getTenderIncreaseAmount() {
        return tenderIncreaseAmount;
    }

    public void setTenderIncreaseAmount(BigDecimal tenderIncreaseAmount) {
        this.tenderIncreaseAmount = tenderIncreaseAmount;
    }

    public String getHxName() {
        return hxName;
    }

    public void setHxName(String hxName) {
        this.hxName = hxName == null ? null : hxName.trim();
    }

    public String getHxAcctNo() {
        return hxAcctNo;
    }

    public void setHxAcctNo(String hxAcctNo) {
        this.hxAcctNo = hxAcctNo == null ? null : hxAcctNo.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc == null ? null : auditDesc.trim();
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime == null ? null : auditTime.trim();
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId == null ? null : auditorId.trim();
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId == null ? null : editorId.trim();
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName == null ? null : editorName.trim();
    }

    public BigDecimal getTenderAmount() {
        return tenderAmount;
    }

    public void setTenderAmount(BigDecimal tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    public BigDecimal getTenderUsers() {
        return tenderUsers;
    }

    public void setTenderUsers(BigDecimal tenderUsers) {
        this.tenderUsers = tenderUsers;
    }

    public String getTenderAuditorId() {
        return tenderAuditorId;
    }

    public void setTenderAuditorId(String tenderAuditorId) {
        this.tenderAuditorId = tenderAuditorId == null ? null : tenderAuditorId.trim();
    }

    public String getTenderAuditorName() {
        return tenderAuditorName;
    }

    public void setTenderAuditorName(String tenderAuditorName) {
        this.tenderAuditorName = tenderAuditorName == null ? null : tenderAuditorName.trim();
    }

    public String getTenderAuditTime() {
        return tenderAuditTime;
    }

    public void setTenderAuditTime(String tenderAuditTime) {
        this.tenderAuditTime = tenderAuditTime == null ? null : tenderAuditTime.trim();
    }

    public String getRepayStartDate() {
        return repayStartDate;
    }

    public void setRepayStartDate(String repayStartDate) {
        this.repayStartDate = repayStartDate == null ? null : repayStartDate.trim();
    }

    public String getRepayEndDate() {
        return repayEndDate;
    }

    public void setRepayEndDate(String repayEndDate) {
        this.repayEndDate = repayEndDate == null ? null : repayEndDate.trim();
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus == null ? null : publishStatus.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId == null ? null : publisherId.trim();
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName == null ? null : publisherName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTenderAuditStatus() {
        return tenderAuditStatus;
    }

    public void setTenderAuditStatus(String tenderAuditStatus) {
        this.tenderAuditStatus = tenderAuditStatus == null ? null : tenderAuditStatus.trim();
    }

    public String getProductAgreementType() {
        return productAgreementType;
    }

    public void setProductAgreementType(String productAgreementType) {
        this.productAgreementType = productAgreementType == null ? null : productAgreementType.trim();
    }

    public BigDecimal getHighSingleInvest() {
        return highSingleInvest;
    }

    public void setHighSingleInvest(BigDecimal highSingleInvest) {
        this.highSingleInvest = highSingleInvest;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getTrustContractNo() {
        return trustContractNo;
    }

    public void setTrustContractNo(String trustContractNo) {
        this.trustContractNo = trustContractNo;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getIfPrepay() {
        return ifPrepay;
    }

    public void setIfPrepay(String ifPrepay) {
        this.ifPrepay = ifPrepay == null ? null : ifPrepay.trim();
    }

    public String getPurchaseDesc() {
        return purchaseDesc;
    }

    public void setPurchaseDesc(String purchaseDesc) {
        this.purchaseDesc = purchaseDesc == null ? null : purchaseDesc.trim();
    }

    public String getProductAdvantage() {
        return productAdvantage;
    }

    public void setProductAdvantage(String productAdvantage) {
        this.productAdvantage = productAdvantage == null ? null : productAdvantage.trim();
    }

    public String getReasonsPrepay() {
        return reasonsPrepay;
    }

    public void setReasonsPrepay(String reasonsPrepay) {
        this.reasonsPrepay = reasonsPrepay == null ? null : reasonsPrepay.trim();
    }

    public String getProjectSummary() {
        return projectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary == null ? null : projectSummary.trim();
    }

    public String getSyAmount() {
        return syAmount;
    }

    public void setSyAmount(String syAmount) {
        this.syAmount = syAmount;
    }

    public String getBfbAmount() {
        return bfbAmount;
    }

    public void setBfbAmount(String bfbAmount) {
        this.bfbAmount = bfbAmount;
    }
}