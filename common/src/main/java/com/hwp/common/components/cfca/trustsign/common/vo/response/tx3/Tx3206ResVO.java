// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3206ResVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.response.tx3;


// Referenced classes of package com.hwp.common.components.com.hwp.common.components.cfca.trustsign.common.vo.response.tx3:
//			Tx3ResVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.SignContractVO;

public class Tx3206ResVO extends Tx3ResVO {

    private SignContractVO signContract;

    public Tx3206ResVO() {
    }

    public SignContractVO getSignContract() {
        return signContract;
    }

    public void setSignContract(SignContractVO signContract) {
        this.signContract = signContract;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3206ResVO [").append(super.toString()).append("\nsignContract=").append(signContract).append("\n]").toString();
    }
}
