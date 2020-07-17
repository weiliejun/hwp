package com.hwp.admin.app.event;

import com.hwp.common.model.spsqxx.bean.Spsqxx;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import org.springframework.context.ApplicationEvent;

public class RybgspSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189242614424169L;
    private Spsqxx spsqxx;

    public RybgspSendEmailEvent(Object source) {
        super(source);
    }

    public RybgspSendEmailEvent(Object source,Spsqxx spsqxx) {
        super(source);
        this.spsqxx = spsqxx;
    }

    public Spsqxx getSpsqxx() {
        return spsqxx;
    }

    public void setSpsqxx(Spsqxx spsqxx) {
        this.spsqxx = spsqxx;
    }
}
