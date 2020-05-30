package com.hwp.common.components.exception;

import com.hwp.common.components.rsa.exception.BaseRuntimeException;

/**
 * @author cyp
 * @time : 2019-01-17 13:47
 * @description 发送消息未定义type异常
 */


public class MessageTypeTmplInexistenceException extends BaseRuntimeException {

    private static final long serialVersionUID = -6793319406941801902L;

    public MessageTypeTmplInexistenceException(String type) {
        super("消息类型模板：" + type + "未定义！");
    }
}
