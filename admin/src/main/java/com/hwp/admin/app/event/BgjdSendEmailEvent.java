package com.hwp.admin.app.event;

import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class BgjdSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189242214429169L;

    private String xmjd1;

    private String xmjd2;

    private Xmxxgl xmxxgl;

    public BgjdSendEmailEvent(Object source) {
        super(source);
    }

    public BgjdSendEmailEvent(Object source, String xmjd1, String xmjd2, Xmxxgl xmxxgl) {
        super(source);
        this.xmjd1 = xmjd1;
        this.xmjd2 = xmjd2;
        this.xmxxgl = xmxxgl;
    }

    public Xmxxgl getXmxxgl() {
        return xmxxgl;
    }

    public void setXmxxgl(Xmxxgl xmxxgl) {
        this.xmxxgl = xmxxgl;
    }

    public String getXmjd1() {
        return xmjd1;
    }

    public void setXmjd1(String xmjd1) {
        this.xmjd1 = xmjd1;
    }

    public String getXmjd2() {
        return xmjd2;
    }

    public void setXmjd2(String xmjd2) {
        this.xmjd2 = xmjd2;
    }
}
