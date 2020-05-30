package com.hwp.admin.components.message.exception;

import com.hwp.common.components.rsa.exception.BaseRuntimeException;

/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2014-3-31
 * @author  zqs
 * ===========================================================================
 *
 */

public class MessageRuntimeException extends BaseRuntimeException {

    private static final long serialVersionUID = 6282589858700134736L;

    public MessageRuntimeException(String message) {
        super(message);
    }
}
