package com.hwp.admin.app.event;

import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class RwscSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189207214464169L;
    private Xmxxgl xmxxgl;

    private Xmrwxx xmrwxx;

    private String name;

    public RwscSendEmailEvent(Object source) {
        super(source);
    }

    public RwscSendEmailEvent(Object source, Xmrwxx xmrwxx, Xmxxgl xmxxgl, String name) {
        super(source);
        this.xmrwxx = xmrwxx;
        this.xmxxgl = xmxxgl;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
