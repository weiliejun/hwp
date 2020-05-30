// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SignContractSignatureAttrVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.cs;

public class SignContractSignatureAttrVO {

    private String contractNo;
    private String signatureOfAttr;

    public SignContractSignatureAttrVO() {
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getSignatureOfAttr() {
        return signatureOfAttr;
    }

    public void setSignatureOfAttr(String signatureOfAttr) {
        this.signatureOfAttr = signatureOfAttr;
    }

    public String toString() {
        return (new StringBuilder()).append("SignContractSignatureAttrVO [\ncontractNo=").append(contractNo).append("\nsignatureOfAttr=").append(signatureOfAttr).append("\n]").toString();
    }
}
