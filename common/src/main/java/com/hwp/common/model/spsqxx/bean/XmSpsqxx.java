package com.hwp.common.model.spsqxx.bean;

public class XmSpsqxx extends Spsqxx {
    public String cpmc;
    private String xmfzrId;

    private String xmfzrName;

    private String xmfzrXx;

    public XmSpsqxx() {
        super();
        this.cpmc = cpmc;
        this.xmfzrId = xmfzrId;
        this.xmfzrName = xmfzrName;
        this.xmfzrXx = xmfzrXx;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getXmfzrId() {
        return xmfzrId;
    }

    public void setXmfzrId(String xmfzrId) {
        this.xmfzrId = xmfzrId;
    }

    public String getXmfzrName() {
        return xmfzrName;
    }

    public void setXmfzrName(String xmfzrName) {
        this.xmfzrName = xmfzrName;
    }

    public String getXmfzrXx() {
        return xmfzrXx;
    }

    public void setXmfzrXx(String xmfzrXx) {
        this.xmfzrXx = xmfzrXx;
    }
}
