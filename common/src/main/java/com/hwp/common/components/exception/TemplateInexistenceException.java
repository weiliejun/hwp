package com.hwp.common.components.exception;


import com.hwp.common.components.rsa.exception.BaseRuntimeException;

/**
 * @author cyp
 * @time : 2019-01-17 13:51
 * 消息模板异常
 */
public class TemplateInexistenceException extends BaseRuntimeException {

    private static final long serialVersionUID = 5408053481973879627L;

    public TemplateInexistenceException(String msg) {
        super(msg);
    }
}
