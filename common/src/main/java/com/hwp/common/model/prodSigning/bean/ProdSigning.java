package com.hwp.common.model.prodSigning.bean;

import java.io.Serializable;

public class ProdSigning implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String uuid;
    private String userId;
    private String prodId;
    private String prodSubId;
    private String fundCifNo;
    private String channelJnlNo;
    private String jsJnlNo;
    private String returnCode;
    private String retuenMsg;
    private String createTime;
    private String updateTime;
    private String dataStatus;
    private String remark;

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

    public String getFundCifNo() {
        return fundCifNo;
    }

    public void setFundCifNo(String fundCifNo) {
        this.fundCifNo = fundCifNo == null ? null : fundCifNo.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}