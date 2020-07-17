package com.hwp.admin.app.event;

import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class RwtxSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189242214464169L;
    private Xmxxgl xmxxgl;

    private Xmrwxx xmrwxx;

    private String rwmc;

    public RwtxSendEmailEvent(Object source) {
        super(source);
    }

    public RwtxSendEmailEvent(Object source, Xmrwxx xmrwxx, Xmxxgl xmxxgl, String rwmc) {
        super(source);
        this.xmrwxx = xmrwxx;
        this.xmxxgl = xmxxgl;
        this.rwmc = rwmc;
    }

    public Xmxxgl getXmxxgl() {
        return xmxxgl;
    }

    public void setXmxxgl(Xmxxgl xmxxgl) {
        this.xmxxgl = xmxxgl;
    }

    public Xmrwxx getXmrwxx() {
        return xmrwxx;
    }

    public void setXmrwxx(Xmrwxx xmrwxx) {
        this.xmrwxx = xmrwxx;
    }

    public String getRwmc() {
        return rwmc;
    }

    public void setRwmc(String rwmc) {
        this.rwmc = rwmc;
    }
}
