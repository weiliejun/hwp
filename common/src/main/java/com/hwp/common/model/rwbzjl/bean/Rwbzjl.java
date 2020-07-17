package com.hwp.common.model.rwbzjl.bean;

import java.io.Serializable;

public class Rwbzjl implements Serializable {
    private String id;

    private String xmId;

    private String rwId;

    private String fstz;

    private String creatorId;

    private String createTime;

    private String remark;

    private String dataStatus;

    private String creatorName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getXmId() {
        return xmId;
    }

    public void setXmId(String xmId) {
        this.xmId = xmId == null ? null : xmId.trim();
    }

    public String getRwId() {
        return rwId;
    }

    public void setRwId(String rwId) {
        this.rwId = rwId == null ? null : rwId.trim();
    }

    public String getFstz() {
        return fstz;
    }

    public void setFstz(String fstz) {
        this.fstz = fstz == null ? null : fstz.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }
}