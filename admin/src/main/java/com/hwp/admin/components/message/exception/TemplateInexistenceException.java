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

public class TemplateInexistenceException extends MessageRuntimeException {

    private static final long serialVersionUID = 5408053481973879627L;

    public TemplateInexistenceException(String msg) {
        super(msg);
    }
}
