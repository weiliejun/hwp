package com.hwp.admin.components.trustsign.event;

import org.springframework.context.ApplicationEvent;

public class TrustSignRegisterEvent extends ApplicationEvent {

    private static final long serialVersionUID = 8085379092301968102L;

    public TrustSignRegisterEvent(Object source) {
        super(source);
    }

}
