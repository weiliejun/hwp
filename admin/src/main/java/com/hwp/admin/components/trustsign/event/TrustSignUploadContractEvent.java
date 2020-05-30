package com.hwp.admin.components.trustsign.event;

import org.springframework.context.ApplicationEvent;

public class TrustSignUploadContractEvent extends ApplicationEvent {

    private static final long serialVersionUID = 6140500866999774350L;

    public TrustSignUploadContractEvent(Object source) {
        super(source);
    }

}
