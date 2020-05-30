// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3206ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.SignContractVO;

public class Tx3206ReqVO extends Tx3ReqVO {

    private SignContractVO signContract;

    public Tx3206ReqVO() {
    }

    public SignContractVO getSignContract() {
        return signContract;
    }

    public void setSignContract(SignContractVO signContract) {
        this.signContract = signContract;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3206ReqVO [").append(super.toString()).append("\nsignContract=").append(signContract).append("\n]").toString();
    }
}
