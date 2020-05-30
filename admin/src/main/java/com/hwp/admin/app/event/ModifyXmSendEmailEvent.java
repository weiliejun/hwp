package com.hwp.admin.app.event;

import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class ModifyXmSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189241214429169L;

    private String diff;

    private Xmxxgl xmxxgl;

    public ModifyXmSendEmailEvent(Object source) {
        super(source);
    }

    public ModifyXmSendEmailEvent(Object source, String diff, Xmxxgl xmxxgl) {
        super(source);
        this.diff = diff;
        this.xmxxgl = xmxxgl;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public Xmxxgl getXmxxgl() {
        return xmxxgl;
    }

    public void setXmxxgl(Xmxxgl xmxxgl) {
        this.xmxxgl = xmxxgl;
    }
}
