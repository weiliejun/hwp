package com.hwp.admin.app.controller;

import com.hwp.common.model.xmrwxx.bean.Xmrwxx;

public class RwfjXmrwxx extends Xmrwxx {
    public String attachFile;

    public RwfjXmrwxx() {
        super();
        this.attachFile = attachFile;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }
}
