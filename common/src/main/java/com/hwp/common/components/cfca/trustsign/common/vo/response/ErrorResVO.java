// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ErrorResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response;

public class ErrorResVO {

    private String errorCode;
    private String errorMessage;

    public ErrorResVO() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return (new StringBuilder()).append("ErrorResVO [\nerrorCode=").append(errorCode).append("\nerrorMessage=").append(errorMessage).append("\n]").toString();
    }
}
