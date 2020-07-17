package com.hwp.common.model.spsqxx.bean;

import java.io.Serializable;

public class Spsqxx implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String glId;
    private String splx;
    private String tzlx;
    private String sqnr;
    private String bgsm;
    private String spr;
    private String sprId;
    private String sprName;
    private String xmjd;
    private String creatorId;
    private String creatorName;
    private String createTime;
    private String remark;
    private String dataStatus;
    private String editTime;
    private Integer editorId;
    private String editorName;
    private String spyj;
    private String spms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGlId() {
        return glId;
    }

    public void setGlId(String glId) {
        this.glId = glId == null ? null : glId.trim();
    }

    public String getSplx() {
        return splx;
    }

    public void setSplx(String splx) {
        this.splx = splx == null ? null : splx.trim();
    }

    public String getTzlx() {
        return tzlx;
    }

    public void setTzlx(String tzlx) {
        this.tzlx = tzlx == null ? null : tzlx.trim();
    }

    public String getSqnr() {
        return sqnr;
    }

    public void setSqnr(String sqnr) {
        this.sqnr = sqnr == null ? null : sqnr.trim();
    }

    public String getBgsm() {
        return bgsm;
    }

    public void setBgsm(String bgsm) {
        this.bgsm = bgsm == null ? null : bgsm.trim();
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr == null ? null : spr.trim();
    }

    public String getSprId() {
        return sprId;
    }

    public void setSprId(String sprId) {
        this.sprId = sprId == null ? null : sprId.trim();
    }

    public String getSprName() {
        return sprName;
    }

    public void setSprName(String sprName) {
        this.sprName = sprName == null ? null : sprName.trim();
    }

    public String getXmjd() {
        return xmjd;
    }

    public void setXmjd(String xmjd) {
        this.xmjd = xmjd == null ? null : xmjd.trim();
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

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName == null ? null : editorName.trim();
    }

    public String getSpyj() {
        return spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj == null ? null : spyj.trim();
    }

    public String getSpms() {
        return spms;
    }

    public void setSpms(String spms) {
        this.spms = spms == null ? null : spms.trim();
    }
}