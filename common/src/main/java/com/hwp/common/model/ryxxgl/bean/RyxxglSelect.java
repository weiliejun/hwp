package com.hwp.common.model.ryxxgl.bean;

import java.io.Serializable;

public class RyxxglSelect implements Serializable {
    private static final long serialVersionUID = 2L;
    private String id;
    private String name;
    private String phone;
    private String gsyx;
    private String gryx;

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

}