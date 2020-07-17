package com.hwp.admin.app.event;

import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class RwzhSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012187242294424169L;
    private Xmxxgl xmxxgl;

    private Xmrwxx xmrwxx;

    public RwzhSendEmailEvent(Object source) {
        super(source);
    }

    public RwzhSendEmailEvent(Object source, Xmrwxx xmrwxx, Xmxxgl xmxxgl) {
        super(source);
        this.xmrwxx = xmrwxx;
        this.xmxxgl = xmxxgl;
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
}
