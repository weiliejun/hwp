package com.hwp.common.model.cfcaTrustsignRecord.bean;

import java.io.Serializable;

public class CfcaTrustsignRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String txCode;
    private String req;
    private String signatrue;
    private String res;
    private String remark;
    private String createTime;
    private String dataStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode == null ? null : txCode.trim();
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req == null ? null : req.trim();
    }

    public String getSignatrue() {
        return signatrue;
    }

    public void setSignatrue(String signatrue) {
        this.signatrue = signatrue == null ? null : signatrue.trim();
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res == null ? null : res.trim();
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
}