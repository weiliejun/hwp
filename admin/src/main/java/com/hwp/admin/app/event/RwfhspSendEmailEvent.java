package com.hwp.admin.app.event;

import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class RwfhspSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189242014424169L;
    private Xmxxgl xmxxgl;

    private Xmrwxx xmrwxx;

    private Spsqxx spsqxx;

    public RwfhspSendEmailEvent(Object source) {
        super(source);
    }

    public RwfhspSendEmailEvent(Object source, Xmrwxx xmrwxx, Xmxxgl xmxxgl, Spsqxx spsqxx) {
        super(source);
        this.xmrwxx = xmrwxx;
        this.xmxxgl = xmxxgl;
        this.spsqxx = spsqxx;
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

    public Spsqxx getSpsqxx() {
        return spsqxx;
    }

    public void setSpsqxx(Spsqxx spsqxx) {
        this.spsqxx = spsqxx;
    }
}
