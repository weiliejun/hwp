// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3204ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;


// Referenced classes of package com.hwp.common.components.com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.GetContractSignatureAttrVO;

public class Tx3204ResVO extends Tx3ResVO {

    private GetContractSignatureAttrVO getContractSignatureAttr;

    public Tx3204ResVO() {
    }

    public GetContractSignatureAttrVO getGetContractSignatureAttr() {
        return getContractSignatureAttr;
    }

    public void setGetContractSignatureAttr(GetContractSignatureAttrVO getContractSignatureAttr) {
        this.getContractSignatureAttr = getContractSignatureAttr;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3204ResVO [").append(super.toString()).append("\ngetContractSignatureAttr=").append(getContractSignatureAttr).append("\n]").toString();
    }
}
