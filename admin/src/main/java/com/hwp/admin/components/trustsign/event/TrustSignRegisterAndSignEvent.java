package com.hwp.admin.components.trustsign.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author 张可乐
 * @version 1.0
 * @description 安心签一键注册和签署事件
 * @update 2017-3-27 下午2:51:46
 */
public class TrustSignRegisterAndSignEvent extends ApplicationEvent {

    private static final long serialVersionUID = 8085379092301968102L;

    public TrustSignRegisterAndSignEvent(Object source) {
        super(source);
    }

}
