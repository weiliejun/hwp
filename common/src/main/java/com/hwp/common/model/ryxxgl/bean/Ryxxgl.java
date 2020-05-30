package com.hwp.common.model.ryxxgl.bean;

import java.io.Serializable;

public class Ryxxgl implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String phone;
    private String gsyx;
    private String gryx;
    private String dept;
    private String status;
    private String createTime;
    private String remark;
    private String dataStatus;
    private String rank;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGsyx() {
        return gsyx;
    }

    public void setGsyx(String gsyx) {
        this.gsyx = gsyx == null ? null : gsyx.trim();
    }

    public String getGryx() {
        return gryx;
    }

    public void setGryx(String gryx) {
        this.gryx = gryx == null ? null : gryx.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }
}