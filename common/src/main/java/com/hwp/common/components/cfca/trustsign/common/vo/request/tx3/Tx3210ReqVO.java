// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tx3210ReqVO.java

package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3;


// Referenced classes of package com.hwp.common.components.cfca.trustsign.common.vo.request.tx3:
//			Tx3ReqVO

import com.hwp.common.components.cfca.trustsign.common.vo.cs.ContractVO;

public class Tx3210ReqVO extends Tx3ReqVO {

    private ContractVO contract;

    public Tx3210ReqVO() {
    }

    public ContractVO getContract() {
        return contract;
    }

    public void setContract(ContractVO contract) {
        this.contract = contract;
    }

    public String toString() {
        return (new StringBuilder()).append("Tx3210ReqVO [").append(super.toString()).append("\ncontract=").append(contract).append("\n]").toString();
    }
}
