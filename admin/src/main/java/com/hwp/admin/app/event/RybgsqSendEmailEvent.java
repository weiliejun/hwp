package com.hwp.admin.app.event;

import com.hwp.common.model.rybgsq.bean.Rybgsq;
import org.springframework.context.ApplicationEvent;

public class RybgsqSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189242224424169L;
    private Rybgsq rybgsq;

    public RybgsqSendEmailEvent(Object source) {
        super(source);
    }

    public RybgsqSendEmailEvent(Object source, Rybgsq rybgsq) {
        super(source);
        this.rybgsq = rybgsq;
    }

    public Rybgsq getRybgsq() {
        return rybgsq;
    }

    public void setRybgsq(Rybgsq rybgsq) {
        this.rybgsq = rybgsq;
    }
}
