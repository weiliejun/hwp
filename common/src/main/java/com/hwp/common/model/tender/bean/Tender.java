package com.hwp.common.model.tender.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tender implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userInfoId;
    private String uuid;
    private String productId;
    private BigDecimal transAmount;
    private String cancelBatchCode;
    private String cancelJsJnlNo;
    private String cancelChannelJnlNo;
    private String cancelRequestContent;
    private String respContent;
    private String returnCode;
    private String returnMsg;
    private String freezeNo;
    private String cancelType;
    private String cancelTime;
    private String cancelRemark;
    private String cancelRespContent;
    private String cancelReturnCode;
    private String cancelReturnMsg;
    private String status;
    private String remark;
    private String createTime;
    private String dataStatus;
    private String terminal;
    private String trsPassword;
    private String batchCode;
    private String jsJnlNo;
    private String channelJnlNo;
    private String verifyTrsPasswordFlag;
    private String requestContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getCancelBatchCode() {
        return cancelBatchCode;
    }

    public void setCancelBatchCode(String cancelBatchCode) {
        this.cancelBatchCode = cancelBatchCode == null ? null : cancelBatchCode.trim();
    }

    public String getCancelJsJnlNo() {
        return cancelJsJnlNo;
    }

    public void setCancelJsJnlNo(String cancelJsJnlNo) {
        this.cancelJsJnlNo = cancelJsJnlNo == null ? null : cancelJsJnlNo.trim();
    }

    public String getCancelChannelJnlNo() {
        return cancelChannelJnlNo;
    }

    public void setCancelChannelJnlNo(String cancelChannelJnlNo) {
        this.cancelChannelJnlNo = cancelChannelJnlNo == null ? null : cancelChannelJnlNo.trim();
    }

    public String getCancelRequestContent() {
        return cancelRequestContent;
    }

    public void setCancelRequestContent(String cancelRequestContent) {
        this.cancelRequestContent = cancelRequestContent == null ? null : cancelRequestContent.trim();
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

    public String getFreezeNo() {
        return freezeNo;
    }

    public void setFreezeNo(String freezeNo) {
        this.freezeNo = freezeNo == null ? null : freezeNo.trim();
    }

    public String getCancelType() {
        return cancelType;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType == null ? null : cancelType.trim();
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime == null ? null : cancelTime.trim();
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark == null ? null : cancelRemark.trim();
    }

    public String getCancelRespContent() {
        return cancelRespContent;
    }

    public void setCancelRespContent(String cancelRespContent) {
        this.cancelRespContent = cancelRespContent == null ? null : cancelRespContent.trim();
    }

    public String getCancelReturnCode() {
        return cancelReturnCode;
    }

    public void setCancelReturnCode(String cancelReturnCode) {
        this.cancelReturnCode = cancelReturnCode == null ? null : cancelReturnCode.trim();
    }

    public String getCancelReturnMsg() {
        return cancelReturnMsg;
    }

    public void setCancelReturnMsg(String cancelReturnMsg) {
        this.cancelReturnMsg = cancelReturnMsg == null ? null : cancelReturnMsg.trim();
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getTrsPassword() {
        return trsPassword;
    }

    public void setTrsPassword(String trsPassword) {
        this.trsPassword = trsPassword == null ? null : trsPassword.trim();
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    public String getJsJnlNo() {
        return jsJnlNo;
    }

    public void setJsJnlNo(String jsJnlNo) {
        this.jsJnlNo = jsJnlNo == null ? null : jsJnlNo.trim();
    }

    public String getChannelJnlNo() {
        return channelJnlNo;
    }

    public void setChannelJnlNo(String channelJnlNo) {
        this.channelJnlNo = channelJnlNo == null ? null : channelJnlNo.trim();
    }

    public String getVerifyTrsPasswordFlag() {
        return verifyTrsPasswordFlag;
    }

    public void setVerifyTrsPasswordFlag(String verifyTrsPasswordFlag) {
        this.verifyTrsPasswordFlag = verifyTrsPasswordFlag == null ? null : verifyTrsPasswordFlag.trim();
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent == null ? null : requestContent.trim();
    }
}