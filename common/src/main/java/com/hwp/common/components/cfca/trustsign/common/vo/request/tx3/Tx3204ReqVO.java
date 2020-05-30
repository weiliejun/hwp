// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3204ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.GetContractSignatureAttrVO;

public class Tx3204ReqVO extends Tx3ReqVO {

    private GetContractSignatureAttrVO getContractSignatureAttr;

    public Tx3204ReqVO() {
    }

    public GetContractSignatureAttrVO getGetContractSignatureAttr() {
        return getContractSignatureAttr;
    }

    public void setGetContractSignatureAttr(GetContractSignatureAttrVO getContractSignatureAttr) {
        this.getContractSignatureAttr = getContractSignatureAttr;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3204ReqVO [").append(super.toString()).append("\ngetContractSignatureAttr=").append(getContractSignatureAttr).append("\n]").toString();
    }
}
