package com.hwp.admin.components.message.exception;

/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2014-3-29
 * @author  zqs
 * ===========================================================================
 *
 */

public class MessageTypeTmplInexistenceException extends MessageRuntimeException {

    private static final long serialVersionUID = -6793319406941801902L;

    public MessageTypeTmplInexistenceException(String type) {
        super("消息类型模板：" + type + "未定义！");
    }
}
