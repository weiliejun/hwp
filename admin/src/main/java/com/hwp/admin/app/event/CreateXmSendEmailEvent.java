package com.hwp.admin.app.event;

import org.springframework.context.ApplicationEvent;

public class CreateXmSendEmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3012189241214429169L;

    public CreateXmSendEmailEvent(Object source) {
        super(source);
    }

}
