// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3205ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.SignContractSignatureAttrVO;

public class Tx3205ReqVO extends Tx3ReqVO {

    private SignContractSignatureAttrVO signContractSignatureAttr;

    public Tx3205ReqVO() {
    }

    public SignContractSignatureAttrVO getSignContractSignatureAttr() {
        return signContractSignatureAttr;
    }

    public void setSignContractSignatureAttr(SignContractSignatureAttrVO signContractSignatureAttr) {
        this.signContractSignatureAttr = signContractSignatureAttr;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3205ReqVO [").append(super.toString()).append("\nsignContractSignatureAttr=").append(signContractSignatureAttr).append("\n]").toString();
    }
}
