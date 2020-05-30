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

public class TemplateParseException extends MessageRuntimeException {

    private static final long serialVersionUID = 217668534548478751L;

    public TemplateParseException() {
        super("消息模板解析异常！");
    }
}
