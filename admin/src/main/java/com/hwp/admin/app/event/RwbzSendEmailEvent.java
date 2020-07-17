package com.hwp.admin.app.event;

import com.hwp.common.model.rwbzjl.bean.Rwbzjl;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class RwbzSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189247214464169L;
    private Xmxxgl xmxxgl;

    private Xmrwxx xmrwxx;

    private Rwbzjl rwbzjl;

    public RwbzSendEmailEvent(Object source) {
        super(source);
    }

    public RwbzSendEmailEvent(Object source, Xmrwxx xmrwxx, Xmxxgl xmxxgl, Rwbzjl rwbzjl) {
        super(source);
        this.xmrwxx = xmrwxx;
        this.xmxxgl = xmxxgl;
        this.rwbzjl = rwbzjl;
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

    public Rwbzjl getRwbzjl() {
        return rwbzjl;
    }

    public void setRwbzjl(Rwbzjl rwbzjl) {
        this.rwbzjl = rwbzjl;
    }
}
