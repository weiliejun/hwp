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

public class MessageBusiTypeTmplInexistenceException extends MessageRuntimeException {

    private static final long serialVersionUID = 8773423080531229172L;

    public MessageBusiTypeTmplInexistenceException(String busiType) {
        super("业务类型模板：" + busiType + "未定义！");
    }
}
